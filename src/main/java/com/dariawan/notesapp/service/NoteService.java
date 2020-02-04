/**
 * Spring Boot + Freemarker Example (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.notesapp.service;

import com.dariawan.notesapp.domain.Note;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.dariawan.notesapp.repository.NoteRepository;

@Service
public class NoteService {
    
    @Autowired
    private NoteRepository noteRepository;
    
    private boolean existsById(Long id) {
        return noteRepository.existsById(id);
    }
    
    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
    
    public List<Note> findAll(int pageNumber, int rowPerPage) {
        List<Note> notes = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("updatedOn").descending());
        noteRepository.findAll(sortedByLastUpdateDesc).forEach(notes::add);
        return notes;
    }
    
    public Note save(Note note) throws Exception {
        if (StringUtils.isEmpty(note.getTitle())) {
            throw new Exception("Title is required");
        }
        if (StringUtils.isEmpty(note.getContent())) {
            throw new Exception("Content is required");
        }
        if (note.getId() != null && existsById(note.getId())) { 
            throw new Exception("Note with id: " + note.getId() + " already exists");
        }
        return noteRepository.save(note);
    }
    
    public void update(Note note) throws Exception {
        if (StringUtils.isEmpty(note.getTitle())) {
            throw new Exception("Title is required");
        }
        if (StringUtils.isEmpty(note.getContent())) {
            throw new Exception("Content is required");
        }
        if (!existsById(note.getId())) {
            throw new Exception("Cannot find Note with id: " + note.getId());
        }
        noteRepository.save(note);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) { 
            throw new Exception("Cannot find Note with id: " + id);
        }
        else {
            noteRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return noteRepository.count();
    }
}
