import java.io.File

// Main function where the program starts
fun main() {
    // Mutable list to store notes
    val notes: MutableList<Note> = mutableListOf()

    // Infinite loop to display menu options until the user chooses to exit
    while (true) {
        println("\n--- Nimbus Notes ---")
        println("1. Create a note")
        println("2. View all notes")
        println("3. Edit a note")
        println("4. Delete a note")
        println("5. Search notes by title")
        println("6. Search notes by content")
        println("7. Save notes to file")
        println("8. Load notes from file")
        println("9. Exit")
        print("Choose an option: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> createNote(notes)
            2 -> viewNotes(notes)
            3 -> editNote(notes)
            4 -> deleteNote(notes)
            5 -> searchNotesByTitle(notes)
            6 -> searchNotesByContent(notes)
            7 -> saveNotesToFile(notes)
            8 -> loadNotesFromFile(notes)
            9 -> {
                println("Thank you for using the Nimbus Notes App! Goodbye!")
                break
            }
            else -> println("Invalid option. Please try again.")
        }
    }


}

// Function to create a new note and add it to the list
fun createNote(notes: MutableList<Note>) {
    print("Enter the title of your note: ")
    val title = readlnOrNull() ?: "" // Read title from the user

    print("Enter your note: ")
    val content = readlnOrNull() ?: "" // Read content from the user

    // Ensure that both the title and content are not empty
    if (title.isBlank() || content.isBlank()) {
        println("Note title and content cannot be empty!") // Warning message
    } else {
        // Assign an ID to the note based on the size of the current notes list
        val id = notes.size + 1
        // Create a new Note object and add it to the notes list
        val note = Note(id, title, content)
        notes.add(note) // Add the new note to the list
        println("Note added!") // Confirmation message
    }
}

// Function to view all notes in the list
fun viewNotes(notes: List<Note>) {
    // Check if the notes list is empty
    if (notes.isEmpty()) {
        println("No notes found.") // Inform the user that there are no notes
    } else {
        println("\n--- Your Notes ---")
        // Loop through each note and display its ID, title, and content
        notes.forEach { note ->
            println("${note.id}. Title: ${note.title}, Content: ${note.content}")
        }
    }
}

fun deleteNote(notes: MutableList<Note>) {
    print("Enter the ID of the note to delete: ")
    val id = readlnOrNull()?.toIntOrNull()

    if (id != null && id > 0 && id <= notes.size) {
        notes.removeAt(id - 1)
        println("Note with ID $id has been deleted.")
    } else {
        println("Invalid ID. Note not found.")
    }
}

fun editNote(notes: MutableList<Note>) {
    print("Enter the ID of the note to edit: ")
    val id = readlnOrNull()?.toIntOrNull()

    if (id != null && id > 0 && id <= notes.size) {
        print("Enter the new title of your note: ")
        val title = readlnOrNull() ?: ""

        print("Enter the new content of your note: ")
        val content = readlnOrNull() ?: ""

        if (title.isBlank() || content.isBlank()) {
            println("Note title and content cannot be empty!")
        } else {
            val note = notes[id - 1]
            notes[id - 1] = note.copy(title = title, content = content)
            println("Note with ID $id has been updated.")
        }
    } else {
        println("Invalid ID. Note not found.")
    }
}

fun saveNotesToFile(notes: List<Note>) {
    val file = File("notes.txt")
    file.printWriter().use { out ->
        notes.forEach { note ->
            out.println("${note.id},${note.title},${note.content}")
        }
    }
    println("Notes have been saved to notes.txt")
}

fun loadNotesFromFile(notes: MutableList<Note>) {
    val file = File("notes.txt")
    if (file.exists()) {
        file.forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 3) {
                val id = parts[0].toInt()
                val title = parts[1]
                val content = parts[2]
                notes.add(Note(id, title, content))
            }
        }
        println("Notes have been loaded from notes.txt")
    } else {
        println("No saved notes found.")
    }
}

fun searchNotesByTitle(notes: List<Note>) {
    print("Enter a title keyword to search: ")
    val keyword = readlnOrNull()?.lowercase() ?: ""

    val results = notes.filter { note ->
        note.title.lowercase().contains(keyword)
    }

    if (results.isEmpty()) {
        println("No notes found matching title '$keyword'.")
    } else {
        println("\n--- Search Results ---")
        results.forEach { note ->
            println("${note.id}. Title: ${note.title}, Content: ${note.content}")
        }
    }
}

fun searchNotesByContent(notes: List<Note>) {
    print("Enter a content keyword to search: ")
    val keyword = readlnOrNull()?.lowercase() ?: ""

    val results = notes.filter { note ->
        note.content.lowercase().contains(keyword)
    }

    if (results.isEmpty()) {
        println("No notes found matching content '$keyword'.")
    } else {
        println("\n--- Search Results ---")
        results.forEach { note ->
            println("${note.id}. Title: ${note.title}, Content: ${note.content}")
        }
    }
}





