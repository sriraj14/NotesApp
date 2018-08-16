package com.app.service;


import com.app.model.User;
import com.app.model.UserNote;
import com.app.repositories.NoteRepository;
import com.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateANoteServiceImpl {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveANoteUsingCurrentUser(UserNote userNote) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(authentication.getName());

        userNote.setUsername(user.getUsername());
        userNote.setTitle(userNote.getTitle());
        userNote.setNoteContent(userNote.getNoteContent());

        noteRepository.save(userNote);
    }

    @Transactional
    public void saveANote(UserNote userNote) {
        noteRepository.save(userNote);
    }


}
