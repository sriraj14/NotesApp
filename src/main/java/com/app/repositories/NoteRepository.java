package com.app.repositories;


import com.app.model.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<UserNote, Long> {

    List<UserNote> findAllByUsernameOrderByTitle(String username);

    UserNote findById(Long id);


}
