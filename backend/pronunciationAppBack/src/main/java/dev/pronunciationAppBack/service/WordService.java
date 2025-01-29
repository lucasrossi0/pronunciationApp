package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    // Method returns the state of the Word being passed as parameter
    public boolean isWordActive(Word word){
        return word.isActive();
    }
}
