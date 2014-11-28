/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import ejbs.ManageFilmRemote;
import ejbs.ManageListFilmsRemote;
import ejbs.ManageProductRemote;
import ejbs.ManageTransactionRemote;
import ejbs.ManageUserRemote;
import ejbs.ManageVideoRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author seb
 */
public class Ejbs {

    private static InitialContext ic = null;
    private static ManageProductRemote mpr = null;
    private static ManageFilmRemote mfr = null;
    private static ManageListFilmsRemote mlfr = null;
    private static ManageUserRemote mur = null;
    private static ManageVideoRemote mvr = null;
    private static ManageTransactionRemote mtr = null;
    private static ManagePersonRemote mpsr = null;

    private static void makeContext() {
	try {
	    Properties props = new Properties();
      //props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
	    //props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
	    //props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
	    //props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
	    //props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

	    ic = new InitialContext();
	} catch (NamingException ex) {
	    Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static ManageFilmRemote film() {
	if (mfr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mfr = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mfr;
    }

    public static ManageListFilmsRemote films() {
	if (mlfr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mlfr = (ManageListFilmsRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageListFilms!ejbs.ManageListFilmsRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mlfr;
    }

    public static ManageProductRemote product() {
	if (mpr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mpr = (ManageProductRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mpr;
    }

    public static ManageUserRemote user() {
	if (mur == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mur;
    }

    public static ManageVideoRemote video() {
	if (mvr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mvr = (ManageVideoRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageVideo!ejbs.ManageVideoRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mvr;
    }

    public static ManageTransactionRemote transaction() {
	if (mtr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mtr = (ManageTransactionRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageTransaction!ejbs.ManageTransactionRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mtr;
    }

    public static ManagePersonRemote person() {
	if (mpsr == null) {
	    try {
		if (ic == null) {
		    makeContext();
		}
		mpsr = (ManagePersonRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManagePerson!ejbs.ManagePersonRemote");
	    } catch (NamingException ex) {
		Logger.getLogger(Ejbs.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return mpsr;
    }

}
