
import javax.swing.JTextArea;

public class Main{
    public static void main(String[] args){
        java.io.PrintWriter out = new java.io.PrintWriter(System.out);
        Reader in = new Reader();
        out.println("Enter mode:\n1. tile map editor\n2. test tile map and overlay\n3. test tile map and overlay with TUI\n4. test GUI\n5. convert png to map");
        out.flush();
        int mode = 0;
        try{mode = in.nextInt();}catch(Exception e){e.printStackTrace();}
        if(mode==1){
            Tilemapmgr.makeMap();
            out.print("Enter map name to draw:");
            out.flush();
            try{out.println(Tilemapmgr.drawcmap(in.readLine().strip()));}catch(Exception e){e.printStackTrace();}
        }else if(mode==2){
            Tilemapmgr.loadFromImage("test", "C:\\Users\\shunt\\apcs\\CSQuest\\tilemaps\\test.png");
            RenderedCMap map=Tilemapmgr.drawcmap("test");
            int x=8, y=8;
            String input="";
            OUTER:
            while (true) {
                map=Tilemapmgr.drawcmap("test", x-2, y-2, 10, 10);
                map.overlay(new RenderedCMap(2, 4, 'X'), 4, 4);
                out.println(map);
                out.flush();
                try{input=in.readLine();}catch(Exception e){e.printStackTrace();}
                switch (input) {
                    case "w" -> y--;
                    case "s" -> y++;
                    case "a" -> x--;
                    case "d" -> x++;
                    case "q" -> {
                        break OUTER;
                    }
                    default -> {
                    }
                }
            }
        }else if(mode==3){
            Tilemapmgr.loadFromImage("test", "C:\\Users\\shunt\\apcs\\CSQuest\\tilemaps\\test.png");
            Tilemapmgr.activate();
            Tilemapmgr.loadTheme("Waterfall");
            Tilemapmgr.setmap("test");
            Graphicsmgr gm = new Graphicsmgr(false);
            ((JTextArea)gm.components.get("Tilemap")).setEditable(false);
            ((JTextArea)gm.components.get("Tilemap")).setFocusable(false);
            gm.panel.setSize(800, 800);
            gm.frame.setSize(800, 800);
            gm.frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            gm.frame.setVisible(true);
            gm.panel.setVisible(true);
            gm.panel.setFocusable(true);
            gm.panel.requestFocus();
            gm.draw();
            gm.panel.addKeyListener(new java.awt.event.KeyAdapter(){
                @Override
                public void keyPressed(java.awt.event.KeyEvent e){
                    switch(e.getKeyCode()){
                        case java.awt.event.KeyEvent.VK_W -> Tilemapmgr.position.y--;
                        case java.awt.event.KeyEvent.VK_S -> Tilemapmgr.position.y++;
                        case java.awt.event.KeyEvent.VK_A -> Tilemapmgr.position.x--;
                        case java.awt.event.KeyEvent.VK_D -> Tilemapmgr.position.x++;
                        case java.awt.event.KeyEvent.VK_Q -> System.exit(0);
                    }
                    gm.draw();
                }
            });
        }else if(mode==4){
            Tilemapmgr.loadMap("test", "C:\\Users\\shunt\\apcs\\CSQuest\\tilemaps\\test.map");
            Tilemapmgr.activate();
            Tilemapmgr.loadTheme("Waterfall");
            Tilemapmgr.setmap("test");
            System.out.println("loaded theme\ndrawing GUI...");
            Graphicsmgr gm = new Graphicsmgr(true);
            gm.panel.setSize(640, 640);
            Inputmgr.attach(gm);
            Inputmgr.addKeyResponsive(gm);
            gm.draw();
            while(true){
                try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();}
                gm.draw();
            }
        }else if(mode==5){
            Tilemapmgr.loadFromImage("test", "C:\\Users\\shunt\\apcs\\CSQuest\\tilemaps\\test.png");
            Tilemapmgr.saveMap("test", "C:\\Users\\shunt\\apcs\\CSQuest\\tilemaps\\test.map");
        }
        out.flush();
    }
}