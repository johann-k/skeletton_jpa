package de.jk;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.Firsttable;

@ServerEndpoint("/echo")
public class EchoAnnotation {

	@OnOpen
	public void open(Session session){
		System.out.println("onopen");
//		save();
		sendbatch(session);
	}
	
    private void sendbatch(Session session) {
		try {
			for (int i = 0; i < 10000; i++) {
				session.getBasicRemote().sendText("NUmber "+i);
				Thread.sleep(300);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void save() {
    	 final String PERSISTENCE_UNIT_NAME2 = "jndiDb";
    	    EntityManagerFactory factory;

    	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME2);
    	    EntityManager em = factory.createEntityManager();
    	    // read the existing entries and write to console
    	    Query q = em.createQuery("SELECT t FROM Firsttable t");
    	    List<Firsttable> todoList = q.getResultList();
    	    for (Firsttable todo : todoList) {
    	      System.out.println(todo);
    	    }
    	    System.out.println("Size: " + todoList.size());
    	    em.getTransaction().begin();
    	 

    	em.getTransaction().commit();
    	    em.close();
		
	}

	@OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
            if (session.isOpen()) {
            	System.out.println(msg);
                session.getBasicRemote().sendText(msg, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb,
            boolean last) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendBinary(bb, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    /**
     * Process a received pong. This is a NO-OP.
     *
     * @param pm    Ignored.
     */
    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        // NO-OP
    }
}
