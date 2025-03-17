class TextState {
    String content;
    TextState next, prev;

    public TextState(String content) {
        this.content = content;
        this.next = this.prev = null;
    }
}

class TextEditor {
    private TextState current;
    private int historyLimit;
    private int historySize;

    public TextEditor(int historyLimit) {
        this.historyLimit = historyLimit;
        this.historySize = 0;
        this.current = null;
    }

    public void addState(String content) {
        TextState newState = new TextState(content);
        if (current != null) {
            newState.prev = current;
            current.next = newState;
        }
        current = newState;
        historySize++;

        if (historySize > historyLimit) {
            removeOldestState();
        }
    }

    private void removeOldestState() {
        TextState temp = current;
        while (temp.prev != null) {
            temp = temp.prev;
        }
        if (temp.next != null) {
            temp.next.prev = null;
        }
        historySize--;
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("No more undo steps available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("No more redo steps available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No content available.");
        }
    }
}

public class UndoRedoTextEditor{
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);
        
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
    }
}
