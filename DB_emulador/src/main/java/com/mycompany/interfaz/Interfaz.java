/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaz;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Interfaz extends JFrame {
    private JTree directoryTree;
    private JTextArea textArea;
    private JTabbedPane tabbedPane;
    private File selectedFile;

    public Interfaz() {
        // Crear el JFileChooser para obtener el path de la carpeta
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            // Crear el JTree con el árbol de directorios/archivos
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(selectedDirectory.getName());
            createNodes(root, selectedDirectory);
            directoryTree = new JTree(root);
            // Agregar listener para la selección en el JTree
            directoryTree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) directoryTree.getLastSelectedPathComponent();
                    if (node == null) return;
                    selectedFile = new File(selectedDirectory.getAbsolutePath() + File.separator + node.getUserObject().toString());
                    if (selectedFile.isFile()) {
                        addTab(selectedFile.getName(), selectedFile);
                    }
                }
            });
            // Crear la ventana principal del editor
            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Tab 1", scrollPane);
            // Configurar la ventana principal
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(directoryTree), tabbedPane);
            splitPane.setDividerLocation(150);
            add(splitPane);
            setTitle("Text Editor");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    // Método para crear nodos en el JTree
    private void createNodes(DefaultMutableTreeNode root, File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(subFile.getName());
                root.add(node);
                createNodes(node, subFile);
            }
        }
    }

    // Método para agregar una pestaña al JTabbedPane
    private void addTab(String title, File file) {
        try {
            FileReader reader = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            int data;
            while ((data = reader.read()) != -1) {
                sb.append((char) data);
            }
            reader.close();
            JTextArea fileContent = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(fileContent);
            JButton saveButton = new JButton("Guardar");
            saveButton.addActionListener(e -> {
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(fileContent.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            JButton closeButton = new JButton("Cerrar");
            closeButton.addActionListener(e -> tabbedPane.remove(tabbedPane.getSelectedIndex()));
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(saveButton, BorderLayout.SOUTH);
            panel.add(closeButton, BorderLayout.NORTH);
            tabbedPane.addTab(title, panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}