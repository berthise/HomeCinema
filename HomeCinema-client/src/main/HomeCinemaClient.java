/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class HomeCinemaClient extends JFrame implements ActionListener {


    public JTextField inp_id;
    public JTextField inp_price;
    public JTextField inp_trailer;
    public JTextField inp_video;
    public JButton send;
    public AdminFilm adFilm;
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run ()
            {
                new HomeCinemaClient().launch();
            }
        });
    }


            public void launch() {
                adFilm = new AdminFilm();
                makeJframe();
                setVisible(true);
            }
    
    public  void makeJframe() {
        setTitle("Admin");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(0, 2));
        this.add(new JLabel("id"));
        inp_id = new JTextField(20);
        this.add(inp_id);
        this.add(new JLabel("prix"));
        inp_price = new JTextField(20);
        this.add(inp_price);
        this.add(new JLabel("trailer"));
        inp_trailer = new JTextField(100);
        this.add(inp_trailer);
        this.add(new JLabel("video"));
        inp_video = new JTextField(100);
        this.add(inp_video);
        send = new JButton("Envoyer");
        send.addActionListener(this);
        this.add(send);

    }
        public void actionPerformed(ActionEvent ae) {
        adFilm.makeAndSendMovie(new Long(inp_id.getText()), inp_video.getText(), inp_trailer.getText(), new Integer(inp_price.getText()));
    }

}


