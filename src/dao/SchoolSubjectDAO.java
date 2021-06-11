package dao;

import pojo.SchoolSubject;

import java.util.List;

public class SchoolSubjectDAO {
        public static List<SchoolSubject> getAllschoolSubject(){
            List<SchoolSubject> results =
                    new Support<SchoolSubject>().
                            executeHql("SELECT st FROM SchoolSubject st");
            return results;
        }

        public static List<SchoolSubject> findID (String schoolSubjectid){
            List<SchoolSubject> results =
                    new Support<SchoolSubject>().
                            executeHql("SELECT st FROM SchoolSubject st WHERE st.subjectId = '" + schoolSubjectid + "'");
            return results;
        }
}
