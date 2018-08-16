package com.app.controllers;

import com.app.model.UserNote;
import com.app.service.EditNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditNoteController {

    @Autowired
    EditNoteService editNoteService;

    @GetMapping(value = "EditNote/{id}")
    public String editNote(@PathVariable("id")Long parsedUserId, Model model){

        UserNote userNote = editNoteService.getUserNote(parsedUserId);
        model.addAttribute("userNote",userNote);

        return "editNote";

    }

    @PostMapping(value = "EditNote/{id}", params = "submit=edit")
    public String saveNote(@ModelAttribute("userNote")UserNote parsedUserNote){

        editNoteService.saveNote(parsedUserNote);

        return "redirect:/myNotes";

    }

    @PostMapping(value = "EditNote/{id}", params = "submit=delete")
    public String deleteNote(@ModelAttribute("userNote")UserNote parsedUserNote){

        editNoteService.deleteNote(parsedUserNote);

        return "redirect:/myNotes";

    }
}
