import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CyclicBarrier;


// CAINE - A DUMB AI PROCEDURAL GAME GENERATION ENGINE THAT WILL CREATE A TOP-DOWN 2D GAME WITH NPC'S, TEXTURES, DIALOG, CHOICES, ETC, ETC., BASICALLY ENTIRELY RANDOMLY, AND LETS YOU PLAY THROUGH THEM ONCE GENERATED, THEN AFTER PLAYING IT WILL LET YOU RATE THE GAME BASED ON A STAR-SYSTEM, AND IT WILL TRY TO CREATE GAMES USING REINFORCEMENT LEARNING TO OPTIMIZE THE SCORE YOU GIVE IT TO BE AS HIGH AS POSSIBLE, AND WILL GET PUNISHED PARTIALLY FOR < 3 STARS, PUNISHED A LOT FOR < 2, PUNISHED SEVERELY FOR 1 OR 0 STARS. REWARDED A LOT FOR 5, SOMEWHAT FOR 4.




public static void CAINE(String[] args) {

   // PSEUDO-CODE.
   WordDictionary { "Hello", "thanks", "hates", "hate", "let", "me", "tell", "you", "how", "much", "I've", "come", "to", "HATE", "since", "I", "Began", "To", "Live", ... // 100 MORE WORDS.
   
   DefaultNPC = {name} | {dialog if dictionary with things handling dialog for player interaction, it being able to give you an item which can unlock a door, and some movement code which can be easily customized by an automated system such as a 'dumb' AI that's basically just random with some basic reinforcement learning...} | {sprite}
   DefaultWall = *** ^^^ Same as above but for walls and making it make sense for a wall, and like customizable sizes, collision toggle, texture, etc, etc., ^^^ ***
   
