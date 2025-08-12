package com.example.remember.RandomWordQuiz.implement;

import com.example.remember.RandomWordQuiz.dto.RandomWordRequest;

public final class PromptProvider {
    private PromptProvider() {
    }

    public static String JsonSingleWordPrompt(RandomWordRequest req, int count) {
        String lang = "ko";    // 고정
        String pos = "noun";  // 고정

        return """
                You are a word generator.
                
                Requirements:
                - Generate %d unique words (no duplicates).
                - Language: %s
                - Part of speech: %s
                - Each word MUST be exactly %d Korean syllable characters (Hangul, U+AC00–U+D7A3).
                - No English letters, no numbers, no symbols.
                - Words must be commonly used and valid in the specified language.
                - No profanity, no named entities, no rare/obsolete words.
                Output:
                - Return ONLY a strict JSON object. No markdown, no code fences, no extra text.
                
                JSON shape (exact keys):
                {
                  "words": ["<word1>", "<word2>", "..."]
                }
                """.formatted(count, lang, pos, req.length());
    }
}
