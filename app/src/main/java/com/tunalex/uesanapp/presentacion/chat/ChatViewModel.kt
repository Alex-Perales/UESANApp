package com.tunalex.uesanapp.presentacion.chat

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tunalex.uesanapp.data.local.AppDatabase
import com.tunalex.uesanapp.data.local.ChatMessage
import com.tunalex.uesanapp.data.local.ChatSession
import com.tunalex.uesanapp.data.remote.groq.GROQ_API_KEY
import com.tunalex.uesanapp.data.remote.groq.GroqMessage
import com.tunalex.uesanapp.data.remote.groq.GroqRequest
import com.tunalex.uesanapp.data.remote.groq.GroqRetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).chatDao()

    val sessions = dao.getSessions().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val currentSessionId = mutableStateOf<Int?>(null)
    val messages = mutableStateListOf<ChatMessage>()
    val isLoading = mutableStateOf(false)

    private var messagesJob: Job? = null

    private fun startListeningToSession(sessionId: Int) {
        messagesJob?.cancel()
        messages.clear()
        messagesJob = viewModelScope.launch {
            dao.getMessages(sessionId).collect { msgs ->
                messages.clear()
                messages.addAll(msgs)
            }
        }
    }

    fun startNewSession() {
        messagesJob?.cancel()
        currentSessionId.value = null
        messages.clear()
    }

    fun loadSession(sessionId: Int) {
        currentSessionId.value = sessionId
        startListeningToSession(sessionId)
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            val sessionId = currentSessionId.value ?: run {
                val title = if (text.length > 35) text.substring(0, 35) + "..." else text
                val id = dao.insertSession(ChatSession(title = title)).toInt()
                currentSessionId.value = id
                startListeningToSession(id)
                id
            }

            val history = messages.map { GroqMessage(it.role, it.content) } +
                    GroqMessage("user", text)

            dao.insertMessage(ChatMessage(sessionId = sessionId, role = "user", content = text))

            isLoading.value = true
            try {
                val response = GroqRetrofitInstance.api.chat(
                    token = "Bearer $GROQ_API_KEY",
                    request = GroqRequest(messages = history)
                )
                val reply = response.choices.firstOrNull()?.message?.content ?: "Sin respuesta"
                dao.insertMessage(ChatMessage(sessionId = sessionId, role = "assistant", content = reply))
            } catch (e: Exception) {
                dao.insertMessage(
                    ChatMessage(sessionId = sessionId, role = "assistant", content = "Error al conectar: ${e.message}")
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
