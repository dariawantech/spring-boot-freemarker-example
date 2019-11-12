package com.dariawan.notesapp.repository;

import com.dariawan.notesapp.domain.Note;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long>, 
        JpaSpecificationExecutor<Note> {
}
