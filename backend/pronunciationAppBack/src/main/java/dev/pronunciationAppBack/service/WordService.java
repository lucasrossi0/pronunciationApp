package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public boolean isWordActive(Word word) {
        return word.isActive();
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(String id) {
        return Optional.ofNullable(wordRepository.getWordById(id));
    }

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public Word updateWord(Word word) {
        return wordRepository.save(word);
    }

    public boolean deleteWord(String id) {
        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllWords() {
        wordRepository.deleteAll();
    }

    public long getWordCount() {
        return wordRepository.count();
    }
}
