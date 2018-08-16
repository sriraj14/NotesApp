package com.app.service;


import com.app.model.UserNote;
import com.app.repositories.NoteRepository;
import com.app.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CreateANoteServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CreateANoteServiceImpl createANoteService;

    private UserNote userNote;

    @Before
    public void before() {
        userNote = new UserNote();

        userNote.setUsername("ANTITO");
        userNote.setTitle("Test title");
        userNote.setNoteContent("Hmm idk what to write really so whatever.");

        noteRepository.delete(userNote);
    }

    @Test
    public void s() {
        createANoteService.saveANote(userNote);
        Assert.assertTrue(noteRepository.findAllByUsernameOrderByTitle("ANTITO") != null);
    }

}
