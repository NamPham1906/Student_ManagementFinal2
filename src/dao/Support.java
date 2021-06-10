package dao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import java.util.List;

public class Support<T> {

    public  List<T> executeHql(String hqlcommand) {
        //start to open session
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<T> objects = null;
        try {
            final String hql = hqlcommand;
            Query query = session.createQuery(hql);

            objects = query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return objects;
    }

    public static boolean stringCompare (char[] input, String passwordstring ){
        String inputstring = "";

        StringBuilder buider = new StringBuilder(input.length);
        for (Character c : input)
            buider.append(c.charValue());
        inputstring = buider.toString();


        if (input.toString() == null ) {
            return false;
        }

        if (inputstring.length() != passwordstring.length()){
            return false;
        }

        if (passwordstring.equals(inputstring)){
            return true;
        }
        else {
            return false;
        }

    }
}
