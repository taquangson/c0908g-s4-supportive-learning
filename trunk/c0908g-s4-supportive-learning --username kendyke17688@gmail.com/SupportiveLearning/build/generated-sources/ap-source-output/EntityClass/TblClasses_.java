package EntityClass;

import EntityClass.TblCurriculums;
import EntityClass.TblUsers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-01-30T23:10:46")
@StaticMetamodel(TblClasses.class)
public class TblClasses_ { 

    public static volatile ListAttribute<TblClasses, TblUsers> tblUsersList;
    public static volatile SingularAttribute<TblClasses, String> classname;
    public static volatile SingularAttribute<TblClasses, TblCurriculums> curriculumID;
    public static volatile SingularAttribute<TblClasses, Date> timestart;
    public static volatile SingularAttribute<TblClasses, String> staffname;

}