package com.maxx.chatbot.controller;

import com.maxx.chatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${cors.allowed-origins:*}")
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return chatService.chat(message);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @DeleteMapping
    public void clearChat() {
        chatService.clearHistory();
    }
}
