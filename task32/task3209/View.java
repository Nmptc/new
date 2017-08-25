package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Администратор on 09.08.2017.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            ExceptionHandler.log(ex);
        }
    }

    public void init()
    {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        setVisible(true);
    }

    public boolean isHtmlTabSelected()
    {
        return tabbedPane.getSelectedIndex()==0;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Новый":
            {
                controller.createNewDocument();
                break;
            }
            case "Открыть":
            {
                controller.openDocument();
                break;
            }
            case "Сохранить":
            {
                controller.saveDocument();
                break;
            }
            case "Сохранить как...":
            {
                controller.saveDocumentAs();
                break;
            }
            case "Выход":
            {
                controller.exit();
                break;
            }
            case "О программе":
            {
                showAbout();
                break;
            }
        }
    }

    public void exit()
    {
        controller.exit();
    }

    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar); //file
        MenuHelper.initEditMenu(this, menuBar); // edit
        MenuHelper.initStyleMenu(this, menuBar); //style
        MenuHelper.initAlignMenu(this, menuBar); // align
        MenuHelper.initColorMenu(this, menuBar); //colorFont
        MenuHelper.initFontMenu(this, menuBar); // fonts
        MenuHelper.initHelpMenu(this, menuBar); //help
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(400, 300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
        switch (tabbedPane.getSelectedIndex())
        {
            case 0:
            {
                controller.setPlainText(plainTextPane.getText());
                break;
            }
            case 1:
            {
                plainTextPane.setText(controller.getPlainText());
                break;
            }
        }

        resetUndo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo()
    {
        try {
            undoManager.undo();
        }
        catch (Exception ex)
        {
            ExceptionHandler.log(ex);
        }
    }

    public void redo()
    {
        try {
            undoManager.redo();
        }
        catch (Exception ex)
        {
            ExceptionHandler.log(ex);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(this, "Типа справка","Справка", JOptionPane.INFORMATION_MESSAGE);
    }
}
