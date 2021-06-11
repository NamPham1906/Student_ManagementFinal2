package dao;

import pojo.Registerperiod;
import java.util.List;


public class RegisterperiodDAO {
    public static List<Registerperiod> getAllRegisterperiod(){
        List<Registerperiod> results =
                new Support<Registerperiod>().
                        executeHql("SELECT st FROM Registerperiod st");
        return results;
    }

    public static List<Registerperiod> findID (String registerperiodid){
        List<Registerperiod> results =
                new Support<Registerperiod>().
                        executeHql("SELECT st FROM Registerperiod st WHERE st.registerperiodId = '" + registerperiodid + "'");
        return results;
    }
}
