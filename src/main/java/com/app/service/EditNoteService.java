package com.app.service;

import com.app.model.UserNote;
import com.app.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditNoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<UserNote> getAllUserNotes() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        List<UserNote> notesList = noteRepository.findAllByUsernameOrderByTitle(currentUser.getName());
        return notesList;
    }

    public UserNote getUserNote(Long userId) {
        return noteRepository.findById(userId);
    }

    public void saveNote(UserNote userNote){
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        userNote.setUsername(currentUser.getName());
        
        noteRepository.save(userNote);
    }

    public void deleteNote (UserNote userNote){
        noteRepository.delete(userNote);

    }

}
