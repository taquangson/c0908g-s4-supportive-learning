package EntityClass;

import EntityClass.TblClasses;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-02T00:23:38")
@StaticMetamodel(TblUsers.class)
public class TblUsers_ { 

    public static volatile SingularAttribute<TblUsers, Date> birthday;
    public static volatile SingularAttribute<TblUsers, String> phone;
    public static volatile SingularAttribute<TblUsers, String> status;
    public static volatile SingularAttribute<TblUsers, TblClasses> classname;
    public static volatile SingularAttribute<TblUsers, String> groupname;
    public static volatile SingularAttribute<TblUsers, byte[]> avatar;
    public static volatile SingularAttribute<TblUsers, String> password;
    public static volatile SingularAttribute<TblUsers, String> username;
    public static volatile SingularAttribute<TblUsers, Date> startdate;
    public static volatile SingularAttribute<TblUsers, String> email;
    public static volatile SingularAttribute<TblUsers, String> address;
    public static volatile SingularAttribute<TblUsers, String> description;
    public static volatile SingularAttribute<TblUsers, String> gender;
    public static volatile SingularAttribute<TblUsers, Date> enddate;
    public static volatile SingularAttribute<TblUsers, String> fullname;

}