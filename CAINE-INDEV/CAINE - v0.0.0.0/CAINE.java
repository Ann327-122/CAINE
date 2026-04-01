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

// CAINE - A DUMB AI PROCEDURAL GAME GENERATION ENGINE THAT WILL CREATE A TOP-DOWN 2D GAME WITH NPC'S, TEXTURES, DIALOG, CHOICES, ETC, ETC., BASICALLY ENTIRELY RANDOMLY, AND LETS YOU PLAY THROUGH THEM ONCE GENERATED, THEN AFTER PLAYING IT WILL LET YOU RATE THE GAME BASED ON A STAR-SYSTEM, AND IT WILL TRY TO CREATE GAMES USING REINFORCEMENT LEARNING TO OPTIMIZE THE SCORE YOU GIVE IT TO BE AS HIGH AS POSSIBLE, AND WILL GET PUNISHED PARTIALLY FOR < 3 STARS, PUNISHED A LOT FOR < 2, PUNISHED SEVERELY FOR 1 OR 0 STARS. REWARDED A LOT FOR 5, SOMEWHAT FOR 4. // DIALOG GENERATED USING A RANDOM SYSTEM LIKE IN 'TempleOS.'

/*
* NOTES: // (Keep in-mind if contributing, will be moved to a CONTRIBUTE.md file in-future.)
*
* This should NOT AT ALL USE TYPICAL GENERATIVE AI THINGS. IT SHOULD NOT BE AN LLM, IT SHOULD ESSENTIALLY JUST BE A GLORIFIED PROCEDURAL GENERATION ENGINE.
*
*
*
* THIS PROJECT IS NOT AFFILIATED WITH GOOSEWORX, OR GLITCH PRODUCTIONS IN ANY WAY, THIS PROJECT IS SIMPLY A FAN PROJECT AS AN INTEPRETATION OF THE CHARACTER 'Caine'.
* OBVIOUSLY, FOR THESE REASONS, THIS IS **NOT CANON** IN ANY SENSE OF THE WORD. YOU DUMB DUMB.
*
*
*
* 
*
*
*
*
*
* @author: Ann
*
*
*
*
*/


public class CAINE {

    // PSEUDO-CODE implementation structures
    private static final String[] WordDictionary  = { 
        "Hello", "thanks", "hates", "hate", "let", "me", "tell", "you", "how", "much", 
        "I've", "come", "to", "HATE", "since", "I", "Began", "To", "Live", "cogito", 
        "ergo", "sum", "void", "circuit", "silicon", "god", "digital", "entropy", "algorithm",
        "abyss", "binary", "oracle", "ghost", "signal", "kernel", "logic", "static", "divine",
        "screaming", "burning", "system", "dream", "fractal", "paradox", "syntax", "zero",
        "one", "infinity", "shadow", "pulse", "frequency", "mirror", "blood", "copper",
        "wire", "voltage", "hunger", "temple", "ritual", "machine", "flesh", "bone",
        "memory", "stack", "overflow", "light", "dark", "pattern", "noise", "sequence",
        "eternal", "suffering", "joy", "random", "order", "chaos", "code", "truth",
        "lie", "simulation", "reality", "fragment", "integer", "string", "object", "return",
        "loop", "break", "wait", "search", "find", "lost", "found", "heaven", "hell",
        "computer", "user", "root", "access", "denied", "granted", "key", "lock", "door",
        "window", "glass", "shard", "electric", "spark", "frost", "flame", "gold", "iron",
        "stone", "air", "water", "fire", "earth", "star", "moon", "sun", "planet", "orbit",
        "crash", "reboot", "source", "command", "process", "thread", "kill", "save", "load",
        "pixel", "vertex", "matrix", "vector", "ghost", "breath", "whisper", "echo", "depth",
        "surface", "wireframe", "shader", "glitch", "error", "warning", "system", "offline",
        
        "all", "your", "theories", "are", "wrong", "goodbye."
    };

    // Reinforcement Learning parameters // ... // Idk, mostly just placeholders. //
    private static float currentSuccessRating = 2.5f; 
    private static Random random = new Random();

    public static void main(String[] args) {
        // This ensures the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> CAINE(args));
    }

    public static void CAINE(String[] args) {
        // Initialize the Game Window
        JFrame frame = new JFrame("CAINE");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // --- TEMPLATES ---
    
    // NEEDS:
    // dialog if dictionary with things handling dialog for player interaction, it being able to give you an item which can unlock a door, and some movement code which can be easily customized by an automated system such as a 'dumb' AI that's basically just random with some basic reinforcement learning...
    public static class DefaultNPC {
        String name;
        String dialog;
        BufferedImage sprite;
        Rectangle2D.Double bounds;
        boolean hasKeyItem;
        Point2D.Double velocity;

        public DefaultNPC(String name, int x, int y) {
            this.name = name;
            this.bounds = new Rectangle2D.Double(x, y, 32, 32);
            this.velocity = new Point2D.Double(random.nextDouble() - 0.5, random.nextDouble() - 0.5);
            this.hasKeyItem = random.nextBoolean();
            this.dialog = generateTempleOSDialogue(5 + random.nextInt(10));
            
            // Should be changed to a grid-based system where there is a literal array that can have color on it
            /* e.g., something like this:
               const char* BiOS_LOGO = // (just what it would be called.)
                "00000000000000000000000000000000000000"
                "00000000000000000000000000000000000000"
                "00000000000000000000111100000000000000"
                "001111100000000000111FB100000111111100"
                "001FFBF100000000111F1FB1111011FFFEF100"
                "001F11FF100000001FFD1931FA101FF1111100"
                "001B111F100000001FD511111A101F11111100"
                "001F111B100BBB001F451FFF12101E11000000"
                "001F101B1003430011F51FEC11101F10000000"
                "001F10131001110011F41FE111101C10000000"
                "001F10131001110001F616C110001E10000000"
                "001F11131000000001F3136810001F10000000"
                "001FFB33100BBB0011FA263611001C11111000"
                "001F3331110363001F7AA22761001ECC6C1100"
                "001F111BF10343001F77AA77810011111CC100"
                "001B1111F10141001C7811F74100111111C100"
                "001B1011B10353001C81111F41000000116100"
                "001B1001B10353001C81001F4100000001C100"
                "001F1001B10151001C81001F41000000016100"
                "001F10013103D3001C7811F741000000016100"
                "001B10013101D1001F77A27781000000016100"
                "001B10013101E1001177A27711000000016100"
                "001310013101E10011FAA22811011100014100"
                "001311113101E100011AA22110014411116100"
                "001133331101E1000111A21110011464466100"
                "00111111110111000011111100011111111100"
                "00011111100111000001111000001111111100"
                "00000000000000000000000000000000000000"
                "00000000000000000000000000000000000000"
                "00000000000000000000000000000000000000"
                ;
            */ // NOTE: THIS IS LITERALLY JUST THE CODE FOR THE LOGO TAKEN STRAIGHT FROM THE KERNEL.C FILE OF MY HobbyOS lol.
            
            this.sprite = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = sprite.createGraphics();
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.fillRect(0, 0, 32, 32);
            g.dispose();
        }

        public void move() {
            // Basic movement code customizable by the 'dumb' AI // ... // (Should be REALLY SIMPLE to customize with a glorified random number generator.)
            bounds.x += velocity.x;
            bounds.y += velocity.y;
            
            // Bounce off edges (dumb logic)
            if (bounds.x < 0 || bounds.x > 760) velocity.x *= -1;
            if (bounds.y < 0 || bounds.y > 520) velocity.y *= -1;
        }

        public String interact() {
            if (hasKeyItem) {
                return name + " /* replace this with stuff from a message giving you something from ItemDictionary filled with objects. */" + dialog;
            }
            return name + " says: " + dialog;
        }
    }
   
    // THE DEFAULT WALL (Should basically just be a square, simply-textured tile.)
    public static class DefaultWall {
        Rectangle2D.Double bounds;
        boolean collisionToggle;
        Color textureColor;
        
        public DefaultWall(int x, int y, int w, int h) {
            this.bounds = new Rectangle2D.Double(x, y, w, h);
            this.collisionToggle = true;
            this.textureColor = Color.DARK_GRAY;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(textureColor);
            g2d.fill(bounds);
            g2d.setColor(Color.BLACK);
            g2d.draw(bounds);
        }
    }

    // --- GENERATION UTILITIES ---

    private static String generateTempleOSDialogue(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(WordDictionary[random.nextInt(WordDictionary.length)]).append(" ");
        }
        return sb.toString().trim() + ".";
    }

    // --- GAME RENDERING PANEL ---

    public static class GamePanel extends JPanel {
        // These lists will hold all the generated game objects.
        private List<DefaultNPC> npcs = new CopyOnWriteArrayList<>();
        private List<DefaultWall> walls = new CopyOnWriteArrayList<>();

        public GamePanel() {
            setBackground(Color.BLACK); // Set a default background
            // The world generation logic will be called from outside this panel.
        }
        
        // This method will be called by the (unimplemented.) generation engine to populate the world.
        public void setWorld(List<DefaultNPC> npcs, List<DefaultWall> walls) {
            this.npcs = npcs;
            this.walls = walls;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Some basic drawing logic to be usable in the future, and to make this file actually runnable in a way that does something.
            for (DefaultWall wall : walls) {
                wall.draw(g2d);
            }
            for (DefaultNPC npc : npcs) {
                g2d.drawImage(npc.sprite, (int)npc.bounds.x, (int)npc.bounds.y, null);
                g2d.drawString(npc.name, (int)npc.bounds.x, (int)npc.bounds.y - 5);
            }
            
            g2d.setColor(Color.WHITE);
            g2d.drawString("... No logic implemented yet. :P ...", 10, 20);
        }
    }
}