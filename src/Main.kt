fun main() {
    val notes: MutableList<Note> = mutableListOf()

    while (true) {
        println("\n--- Note-Taking App ---")
        println("1. Create a note")
        println("2. View all notes")
        println("3. Exit")
        print("Choose an option: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> createNote(notes)
            2 -> viewNotes(notes)
            3 -> break
            else -> println("Invalid option. Please try again.")
        }
    }
}

fun createNote(notes: MutableList<Note>) {
    print("Enter the title of your note: ")
    val title = readlnOrNull() ?: ""

    print("Enter your note: ")
    val content = readlnOrNull() ?: ""

    if (title.isBlank() || content.isBlank()) {
        println("Note title and content cannot be empty!")
    } else {
        val id = notes.size + 1 // Simple ID assignment
        val note = Note(id, title, content)
        notes.add(note)
        println("Note added!")
    }
}

fun viewNotes(notes: List<Note>) {
    if (notes.isEmpty()) {
        println("No notes found.")
    } else {
        println("\n--- Your Notes ---")
        notes.forEach { note ->
            println("${note.id}. Title: ${note.title}, Content: ${note.content}")
        }
    }
}
