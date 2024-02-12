package keepnotespring.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import keepnotespring.model.Note;

/*
 * This class contains the code for data storage interactions and methods 
 * of this class will be used by other parts of the applications such
 * as Controllers and Test Cases
 * */
@Service
public class NoteRepository {

	/* Declare a variable called "list" to store all the notes. */

	public static List<Note> list;

	public NoteRepository() {

		this.list = new ArrayList<>();

		Note n1 = new Note();

		n1.setNoteId(1);
		n1.setNoteTitle("supra");
		n1.setNoteContent("its a super car");
		n1.setNoteStatus("success");

		list.add(n1);
		/* Initialize the variable using proper data type */
	}

	/* This method should return all the notes in the list */

	public List<Note> getList() {

		return list;
	}

	/* This method should set the list variable with new list of notes */

	public void setList(List<Note> list1) {

		this.list = list1;

	}

	/*
	 * This method should Note object as argument and add the new note object into
	 * list
	 */

	public void addNote(Note note) {
		list.add(note);

	}

	/* This method should deleted a specified note from the list */

	public boolean deleteNote(int noteId) {
		/* Use list iterator to find matching note id and remove it from the list */

		boolean removeIf = list.removeIf(note -> note.getNoteId() == noteId);
		return removeIf;

	}

	/* This method should return the list of notes */

	public List<Note> getAllNotes() {
		return list;
	}

	/*
	 * This method should check if the matching note id present in the list or not.
	 * Return true if note id exists in the list or return false if note id does not
	 * exists in the list
	 */

	public boolean exists(int noteId) {

		Note note = list.stream().filter(s -> s.getNoteId() == noteId).findFirst().get();
		return false;
	}

	public void updatenote(Note n, int id) {

		Note note = list.stream().filter(s -> s.getNoteId() == id).findFirst().get();

		if (note != null) {
			note.setNoteTitle(n.getNoteTitle());
			note.setNoteContent(n.getNoteContent());
			note.setNoteStatus(n.getNoteStatus());

		}

	}

	public Note getNoteByid(int id) {

		Note note = list.stream().filter(s -> s.noteId == id).findFirst().get();

		return note;

	}
}