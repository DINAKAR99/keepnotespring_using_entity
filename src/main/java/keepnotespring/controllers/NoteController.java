package keepnotespring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import keepnotespring.dao.NoteDAO;
import keepnotespring.dao.NoteDAOImpl;
import keepnotespring.model.Note;
import keepnotespring.repositories.NoteRepository;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	@Autowired
	public NoteDAO np;

	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note
	 * should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and
	 * status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	@GetMapping("/")
	public String getAllNotes(Model m) {

		List<Note> allNotes = np.getAllNotes();

		m.addAttribute("notespack", allNotes);

		return "home";

	}

	@GetMapping("/showform")
	public String showform(Model m) {
		List<Note> allNotes = null;
		// np.getAllNotes()
		m.addAttribute("notespack", allNotes);

		m.addAttribute("flag", true);

		return "home";

	}

	@PostMapping("/addnote")
	public String addNote(@ModelAttribute Note n1, Model m) {

		np.saveNote(n1);

		List<Note> allNotes = np.getAllNotes();

		m.addAttribute("notespack", allNotes);

		m.addAttribute("message", "note added success");

		return "home";

	}

	@PostMapping("/updatenote/{id}")
	public String updateNotes(@ModelAttribute Note n1, @PathVariable("id") int id, Model m, RedirectAttributes r1) {

		np.UpdateNote(n1);

		List<Note> allNotes = np.getAllNotes();

		r1.addFlashAttribute("notespack", allNotes);

		r1.addFlashAttribute("message", "note edited success");

		return "redirect:/";

	}

	@GetMapping("/updatenoteclick/{id}")
	public String updateNoteclick(@PathVariable("id") int id,
			RedirectAttributes r1) {

		r1.addFlashAttribute("edit", "true");
		Note noteByid = np.getNoteById(id);

		r1.addFlashAttribute("editnote", noteByid);
		r1.addFlashAttribute("message", "in update ");

		// RedirectView redirectView = new RedirectView();
		// redirectView.setUrl(request.getContextPath() + "/");

		return "redirect:/";

	}

	@GetMapping("/deletenote/{id}")
	public String deleteNotes(@PathVariable("id") int id, Model m, HttpServletRequest request, RedirectAttributes r1) {

		boolean deleteNote = np.deleteNote(id);

		// List<Note> allNotes = np.getAllNotes();

		// RedirectView redirectView = new RedirectView();
		// redirectView.setUrl(request.getContextPath() + "/");

		if (deleteNote == true) {

			// m.addAttribute("notespack", allNotes);
			r1.addFlashAttribute("message", "note delete success");
			return "redirect:/";
		} else {

			// r1.addAttribute("notespack", allNotes);
			r1.addFlashAttribute("message", "note delete fail");
			return "redirect:/";
		}

	}

	/*
	 * Get the application context from resources/beans.xml file using
	 * ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */

	/*
	 * Define a handler method to read the existing notes by calling the
	 * getAllNotes() method
	 * of the NoteRepository class and add it to the ModelMap which is an
	 * implementation of Map
	 * for use when building model data for use with views. it should map to the
	 * default URL i.e. "/"
	 */

	/*
	 * Define a handler method which will read the Note data from request parameters
	 * and
	 * save the note by calling the addNote() method of NoteRepository class. Please
	 * note
	 * that the createdAt field should always be auto populated with system time and
	 * should not be accepted
	 * from the user. Also, after saving the note, it should show the same along
	 * with existing
	 * notes. Hence, reading notes has to be done here again and the retrieved notes
	 * object
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote".
	 */

	/*
	 * Define a handler method to delete an existing note by calling the
	 * deleteNote() method
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote"
	 */

}