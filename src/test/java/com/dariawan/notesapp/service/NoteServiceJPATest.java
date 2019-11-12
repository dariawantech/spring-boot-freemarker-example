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
