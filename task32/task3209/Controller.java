package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by Администратор on 09.08.2017.
 */
public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public void init()
    {
        createNewDocument();
    }

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args)
    {
        View cview = new View();
        Controller controller = new Controller(cview);
        cview.setController(controller);
        cview.init();
        controller.init();
    }

    public void exit()
    {
        System.exit(0);
    }

    public void resetDocument()
    {
        UndoListener undoListener = view.getUndoListener();
        if(document!=null)
            document.removeUndoableEditListener(undoListener);
        document = (HTMLDocument) (new HTMLEditorKit().createDefaultDocument());
        document.addUndoableEditListener(undoListener);
        view.update();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void setPlainText(String text)
    {
        resetDocument();
        try (StringReader stringReader = new StringReader(text)) {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (IOException|BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        try (StringWriter stringWriter = new StringWriter())
        {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
            return stringWriter.toString();
        } catch (IOException|BadLocationException e) {
            ExceptionHandler.log(e);
        }

        return "";
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if(fileChooser.showOpenDialog(view)==JFileChooser.APPROVE_OPTION)
        {
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            }
            catch (Exception ex)
            {
                ExceptionHandler.log(ex);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null) saveDocumentAs();
        else {
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
            }
            catch (Exception ex)
            {
                ExceptionHandler.log(ex);
            }
        }

    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if(fileChooser.showSaveDialog(view)==JFileChooser.APPROVE_OPTION)
        {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
            }
            catch (Exception ex)
            {
                ExceptionHandler.log(ex);
            }
        }
    }
}
