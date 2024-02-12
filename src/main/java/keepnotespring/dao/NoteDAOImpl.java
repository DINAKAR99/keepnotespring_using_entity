package keepnotespring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import keepnotespring.model.Note;

// import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@Autowired
	public SessionFactory s1;

	/*
	 * Save the note in the database(note) table.
	 */
	@Transactional
	public boolean saveNote(Note note) {

		s1.getCurrentSession().save(note);
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */
	@Transactional
	public boolean deleteNote(int noteId) {

		Note note = s1.getCurrentSession().get(Note.class, noteId);
		s1.getCurrentSession().delete(note);

		if (note != null) {

			return true;
		}

		return false;
	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	@Transactional
	public List<Note> getAllNotes() {

		String query = "from Note";

		List<Note> resultList = s1.getCurrentSession().createQuery(query, Note.class).getResultList();
		return resultList;

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	@Transactional
	public Note getNoteById(int noteId) {
		System.out.println("thr id is " + noteId);

		Note note = s1.getCurrentSession().get(Note.class, noteId);
		return note;

	}

	/* Update existing note */
	@Transactional
	public boolean UpdateNote(Note note) {
		System.out.println(note);

		System.out.println("the note id " + note.noteId);

		s1.getCurrentSession().merge(note);

		return true;

	}

}
