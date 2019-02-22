import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PMS_Monitor2.java
 *
 * Created on May 7, 2015, 7:13:53 PM
 */

/**
 *
 * @author master
 */
public class PMS_Monitor2 extends java.awt.Frame implements SerialPortEventListener {

  javax.swing.JFrame j1 = new javax.swing.JFrame();
  javax.swing.JLabel l1= new javax.swing.JLabel();


    SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = {"COM3"};
	/**
	* A BufferedReader which will be fed by a InputStreamReader
	* converting the bytes into characters
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
                // the next line is for Raspberry Pi and
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
                System.setProperty("gnu.io.rxtx.SerialPorts", "COM3");

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {

                            
                            





				String inputLine=input.readLine();

                            if(inputLine.equals("i d 1 _ c a r _ p a r k e d "))
                                {
                                    System.out.println("id1 car prkd k andr");
                                       l1.setText("id1 car prkd k andr");

                                }
                                if(inputLine.equals("i d 1 _ c a r _ n o t _ p a r k e d "))
                                {
                                    System.out.println("id1 car not prkd k andr");
                                    l1.setText("id1 car not prkd k andr");
                                    
                                }
                                
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

        
    /** Creates new form PMS_Monitor2 */
    public PMS_Monitor2() {
        initComponents();
  j1.setBounds(300, 200, 400, 700);
  j1.setVisible(true);
  l1.setText("ram");
  j1.add(l1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        id1 = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id1.setVisible(id1b);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pms2.PMS2App.class).getContext().getResourceMap(PMS_Monitor2.class);
        id1.setIcon(resourceMap.getIcon("id1.icon")); // NOI18N
        jPanel1.add(id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, 150));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 482, 365));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
        id1.setVisible(false);
        this.setTitle("workde");
    }//GEN-LAST:event_formComponentHidden

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {

                PMS_Monitor2 main = new PMS_Monitor2();
		main.initialize();
                
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PMS_Monitor2().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id1;
    Boolean id1b = true;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
