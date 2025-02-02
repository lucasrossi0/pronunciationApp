package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        HttpHeaders headers = getCommonHeaders("Hello endpoint");
        return new ResponseEntity<>("hello Emiliano, are you sleeping?", headers, HttpStatus.OK);
    }

    @GetMapping("/isActive")
    public ResponseEntity<String> isWordActive(@RequestBody Word word) {
        boolean isActive = wordService.isWordActive(word);
        HttpHeaders headers = getCommonHeaders("Check if word is active");
        return new ResponseEntity<>(isActive ? "Active" : "Not active", headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> words = wordService.getAllWords();
        HttpHeaders headers = getCommonHeaders("Get all words");

        return !words.isEmpty()
                ? new ResponseEntity<>(words, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable String id) {
        return wordService.getWordById(id)
                .map(word -> new ResponseEntity<>(word, getCommonHeaders("Get word by ID"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(getCommonHeaders("Get word by ID"), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createWord")
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordService.createWord(word);
        HttpHeaders headers = getCommonHeaders("Create a new word");
        return new ResponseEntity<>(createdWord, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable String id, @RequestBody Word word) {
        if (!id.equals(word.getId())) {
            return new ResponseEntity<>(getCommonHeaders("Update a word"), HttpStatus.BAD_REQUEST);
        }
        Word updatedWord = wordService.updateWord(word);
        HttpHeaders headers = getCommonHeaders("Update a word");
        return new ResponseEntity<>(updatedWord, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWord(@PathVariable("id") String idToDelete) {
        HttpHeaders headers = getCommonHeaders("Delete a word");
        boolean deleted = wordService.deleteWord(idToDelete);
        if (deleted) {
            return new ResponseEntity<>("Word deleted", headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Word not found", headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllWords() {
        wordService.deleteAllWords();
        HttpHeaders headers = getCommonHeaders("Delete all words");
        return new ResponseEntity<>("All words deleted", headers, HttpStatus.OK);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "Spring Boot");
        headers.add("version", "1.0.0");
        headers.add("word-count", String.valueOf(wordService.getWordCount()));
        headers.add("object", "words");
        return headers;
    }
}
