package notetaker;

import java.awt.Component;
import java.io.File;
import java.io.FileFilter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

public class Class3Notes extends JFrame
{
    public Component getGui(File[] all, boolean vertical) 
    {
        JList fileList = new JList(all);
        fileList.setCellRenderer(new FileRenderer(!vertical));

        if (!vertical)
        {
            fileList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
            fileList.setVisibleRowCount(-1);
        } 
        
        else 
        {
            fileList.setVisibleRowCount(9);
        }

        return new JScrollPane(fileList);
    }

    class TextFileFilter implements FileFilter
    {
        @Override
        public boolean accept(File file)
        {
            String name = file.getName().toLowerCase();
            return name.length() < 20;
        }
    }

    class FileRenderer extends DefaultListCellRenderer
    {
        private final boolean pad;
        private final Border padBorder = new EmptyBorder(3, 3, 3, 3);

        FileRenderer(boolean pad) 
        {
            this.pad = pad;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
        {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            JLabel l = (JLabel) c;
            File f = (File) value;
            l.setText(f.getName());
            l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));

            if (pad) 
            {
                l.setBorder(padBorder);
            }

            return l;
        }
    }
}