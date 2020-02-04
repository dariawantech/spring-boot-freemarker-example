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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceJPATest {

    @Autowired 
    private DataSource dataSource;
    
    @Autowired 
    private NoteService noteService;
    
    @Before
    public void cleanTestData() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "delete from notes where title <> ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Hello World!");
            ps.executeUpdate();
        }
    }
    
    
    @Test
    public void testFindAllNotes() {
        List<Note> notes = noteService.findAll(1, 20);
        assertNotNull(notes);
        assertTrue(notes.size() == 1);
        for (Note user : notes) {
            assertNotNull(user.getId());
            assertNotNull(user.getTitle());
            assertNotNull(user.getContent());
        }
    }
    
    @Test
    public void testSaveUpdateDeleteNote() throws Exception{
        Note n = new Note();
        n.setTitle("Spring Boot + FreeMarker");
        n.setContent("Spring Boot with FreeMarker Tutorial");
        
        noteService.save(n);
        assertNotNull(n.getId());
        
        Note findNote = noteService.findById(n.getId());
        assertEquals(n.getTitle(), findNote.getTitle());
        assertEquals(n.getContent(), findNote.getContent());
        
        // update record
        n.setTitle("Spring Boot + FreeMarker Example");
        noteService.update(n);
        
        // test after update
        findNote = noteService.findById(n.getId());
        assertEquals(n.getTitle(), findNote.getTitle());
        
        // test delete
        noteService.deleteById(n.getId());
        
        // query after delete
        Note nDel = noteService.findById(n.getId());
        assertNull(nDel);
    }
}
