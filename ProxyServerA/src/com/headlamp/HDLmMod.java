package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Class for supporting HTML modifications
 *
 * Each instance of this class describes one HTML modification. The instance has
 * information about how to find the HTML element (of elements) that need to be
 * changed. The instance also describes the change that must be made.
 *
 * This class has a constructor that builds an instance from JSON describing an
 * instance. The class is designed so that the modifications can be quickly
 * applied. The conversion from JSON to a class instance may not be fast at all.
 *
 * This class also has methods for applying a modification to a set of HTML. The
 * apply method(s) find the HTML elements that need to be modified and change
 * them as need be.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmMod {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
private static final Logger LOG = LoggerFactory.getLogger(HDLmMod.class); 
	/* The following JSON data structure is used to build the editor
     for each type of modification. The modification type is used
     as the property name to obtain each set of modification data. */
static String HDLmModInfoDataString =
" {" +
"   \"attribute\":       { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extraAttribute\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }" +
"                          ]" + 
"                        }," +
"   \"changeattrs\":     { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Change Attributes Values\"," +
"                              \"source\":        \"changeattrsvalues\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"changeattrs\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"extract\":         { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"fontcolor\":       { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Colors\"," +
"                              \"source\":        \"colors\"," +
"                              \"fieldtype\":     \"colorlist\"," +
"                              \"subtype\":       \"colors\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"fontfamily\":      { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Families\"," +
"                              \"source\":        \"families\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"fontfamily\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"fontkerning\":     { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +                       
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Kernings\"," +
"                              \"source\":        \"kernings\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"fontkerning\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"fontsize\":        { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Sizes\"," +
"                              \"source\":        \"sizes\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"fontsize\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"fontstyle\":       { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Styles\"," +
"                              \"source\":        \"styles\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"fontstyle\"," +
"                              \"datatype\":      \"array\"" + 
"                            }" +
"                          ]" +
"                        }," +
"   \"fontweight\":      { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Font Weights\"," +
"                              \"source\":        \"weights\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"fontweight\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"height\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Heights\"," +
"                              \"source\":        \"heights\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"height\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"image\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Images\"," +
"                              \"source\":        \"images\"," +
"                              \"fieldtype\":     \"imagelist\"," +
"                              \"subtype\":       \"images\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"modify\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"newcompmod\":      { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Company Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"editablecompmodname\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"newdivision\":     { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Division Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"editabledivisionname\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"newmod\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"editablemodificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"editableparameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"newsite\":         { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Site Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"editablesitename\"" +
"                            }" +        
"                          ]" +
"                        }," +
"   \"notify\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extract Targets\"," +
"                              \"source\":        \"targets\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"target\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"order\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"New Order Information\"," +
"                              \"source\":        \"orders\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"order\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +    
"   \"remove\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Remove Values\"," +
"                              \"source\":        \"removevalues\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"remove\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +     
"                        }," +
"   \"replace\":         { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Replace Values\"," +
"                              \"source\":        \"replacevalues\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"replace\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"script\":          { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Scripts\"," +
"                              \"source\":        \"scripts\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"script\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"style\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extraStyle\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"New Style Information\"," +
"                              \"source\":        \"styles\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"style\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"text\":            { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"New Texts\"," +
"                              \"source\":        \"newtexts\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"text\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"textchecked\":     { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"New Texts\"," +
"                              \"source\":        \"newtexts\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"textchecked\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"title\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"New Titles\"," +
"                              \"source\":        \"titles\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"title\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"visit\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Visit Values\"," +
"                              \"source\":        \"visitvalues\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"visit\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }," +
"   \"width\":           { \"fields\":" +
"                          [" +
"                            {" +
"                              \"description\":   \"Modification Name\"," +
"                              \"source\":        \"name\"," +
"                              \"fieldtype\":     \"iotext\"," +
"                              \"subtype\":       \"modificationname\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Path Value\"," +
"                              \"source\":        \"pathvalue\"," +
"                              \"fieldtype\":     \"pathvalue\"," +
"                              \"subtype\":       \"Path Value\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Comments\"," +
"                              \"source\":        \"comments\"," +
"                              \"fieldtype\":     \"comminfo\"," +
"                              \"subtype\":       \"comments\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Extra Information\"," +
"                              \"source\":        \"extra\"," +
"                              \"fieldtype\":     \"extrainfo\"," +
"                              \"subtype\":       \"extra\"" +
"                            }," +
"														 {" +
"															 \"description\":   \"Use Mode\"," +
"															 \"source\":        \"usemode\"," +
"															 \"fieldtype\":     \"usemode\"," +
"															 \"subtype\":       \"usemode\"" +
"														 }," +
"                            {" +
"                              \"description\":   \"Created\"," +
"                              \"source\":        \"created\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Last Modified\"," +
"                              \"source\":        \"lastmodified\"," +
"                              \"fieldtype\":     \"dateio\"," +
"                              \"subtype\":       \"outputdate\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"CSS Selector\"," +
"                              \"source\":        \"cssselector\"," +
"                              \"fieldtype\":     \"cssinfo\"," +
"                              \"subtype\":       \"cssselector\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"XPath Information\"," +
"                              \"source\":        \"xpath\"," +
"                              \"fieldtype\":     \"xpathinfo\"," +
"                              \"subtype\":       \"xpath\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Find Information\"," +
"                              \"source\":        \"find\"," +
"                              \"fieldtype\":     \"findinfo\"," +
"                              \"subtype\":       \"find\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Node Identifier\"," +
"                              \"source\":        \"nodeiden\"," +
"                              \"fieldtype\":     \"nodeiden\"," +
"                              \"subtype\":       \"nodeiden\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Enabled\"," +
"                              \"source\":        \"enabled\"," +
"                              \"fieldtype\":     \"checkbox\"," +
"                              \"subtype\":       \"checkbox\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Parameter Number\"," +
"                              \"source\":        \"parameter\"," +
"                              \"fieldtype\":     \"ionumber\"," +
"                              \"subtype\":       \"parameter\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Modification Type\"," +
"                              \"source\":        \"type\"," +
"                              \"fieldtype\":     \"typelist\"," +
"                              \"subtype\":       \"editabletypelist\"" +
"                            }," +
"                            {" +
"                              \"description\":   \"Widths\"," +
"                              \"source\":        \"widths\"," +
"                              \"fieldtype\":     \"textlist\"," +
"                              \"subtype\":       \"width\"," +
"                              \"datatype\":      \"array\"" +
"                            }" +
"                          ]" +
"                        }" +
" }";
	/* The next JSON object contains some name information about
     each type of modification */
	static String HDLmModTypeInfoString = "{" +
	   "\"attribute\":   { \"extraused\": true,  \"parmnumberused\": false, \"longname\": \"attribute\"," +
	                      "\"valuesname\": null }," +
	   "\"changeattrs\": { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"change attributes\"," +
                        "\"valuesname\": \"changeattrsvalues\" }," +	   
	   "\"changenodes\": { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"change nodes\"," +
                        "\"valuesname\": \"changenodesvalues\" }," +   
	   "\"extract\":     { \"extraused\": true,  \"parmnumberused\": false, \"longname\": \"extract\"," +
     				            "\"valuesname\": null }," +
	   "\"fontcolor\":   { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font color\"," +
                        "\"valuesname\": \"colors\" }," +
	   "\"fontfamily\":  { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font family\"," +
                        "\"valuesname\": \"families\" }," +
	   "\"fontkerning\": { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font kerning\"," +
                        "\"valuesname\": \"kernings\" }," +
	   "\"fontsize\":    { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font size\"," +
                        "\"valuesname\": \"sizes\" }," +
	   "\"fontstyle\":   { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font style\"," +
                        "\"valuesname\": \"styles\" }," +
	   "\"fontweight\":  { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"font weight\"," +
                        "\"valuesname\": \"weights\" }," +
	   "\"height\":      { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"height\"," +
                        "\"valuesname\": \"heignts\" }," +
	   "\"image\":       { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"image\"," +
                        "\"valuesname\": \"images\" }," +
	   "\"modify\":      { \"extraused\": true,  \"parmnumberused\": false, \"longname\": \"modify\"," +
                        "\"valuesname\": null }," +
	   "\"notify\":      { \"extraused\": true,  \"parmnumberused\": false, \"longname\": \"notify\"," +
                        "\"valuesname\": \"targets\" }," +
	   "\"order\":       { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"order\"," +
                        "\"valuesname\": \"orders\" }," +
	   "\"remove\":      { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"remove\"," +
                        "\"valuesname\": \"removevalues\" }," +
	   "\"replace\":     { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"replace\"," +
                        "\"valuesname\": \"replacevalues\" }," +
     "\"script\":      { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"script\"," +
                        "\"valuesname\": \"scripts\" }," +
	   "\"style\":       { \"extraused\": true,  \"parmnumberused\": true,  \"longname\": \"style\"," +
                        "\"valuesname\": \"styles\" }," +
	   "\"text\":        { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"text\"," +
                        "\"valuesname\": \"newtexts\" }," +
	   "\"textchecked\": { \"extraused\": true,  \"parmnumberused\": true,  \"longname\": \"checked type\"," +
                        "\"valuesname\": \"newtexts\" }," +
	   "\"title\":       { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"title\"," +
                        "\"valuesname\": \"titles\" }," +
     "\"visit\":       { \"extraused\": true,  \"parmnumberused\": true,  \"longname\": \"visit\"," +
                        "\"valuesname\": \"visitvalues\" }," +
	   "\"width\":       { \"extraused\": false, \"parmnumberused\": true,  \"longname\": \"width\"," +
                        "\"valuesname\": \"widths\" }" +
	"}";
	/* The next JSON string contains some name information about
		 each type of tree node. Note that we have two or more entries 
		 for company nodes. However, no entry has a key of 'company'.
		 Instead, more specific keys are used. The actual keys are
		 'compmod' for company nodes used for modifications and
		 'compproxy' for company nodes used proxy definitions. */
static String HDLmModTreeInfoString = "{" + 
	 "  \"companies\": {" +
	 "    \"longname\":    \"companies\"," +
	 "	  \"ucfirstname\": \"Companies\"," +
	 "    \"tooltip\":     \"Companies node\"" +
	 "  }," +
	 "  \"compignore\": {" +
	 "    \"longname\":    \"company\"," +
	 "	  \"ucfirstname\": \"Company\"," +
	 "    \"tooltip\":     \"Company node\"" +
	 "  }," +
	 "  \"compmod\": {" +
	 "    \"longname\":    \"company\"," +
	 "	  \"ucfirstname\": \"Company\"," +
	 "  	\"tooltip\":     \"Company node\"" +
	 "  }," +
	 "  \"comppass\": {" +
	 "    \"longname\":    \"company\"," +
	 "  	\"ucfirstname\": \"Company\"," +
	 "	  \"tooltip\":     \"Company node\"" +
	 "  }," +
	 "  \"compproxy\": {" +
	 "    \"longname\":    \"company\"," +
	 "  	\"ucfirstname\": \"Company\"," +
	 "	  \"tooltip\":     \"Company node\"" +
	 "  }," +
	 "  \"compstore\": {" +
	 "    \"longname\":    \"company\"," +
	 "    \"ucfirstname\": \"Company\"," +
	 "    \"tooltip\":     \"Company node\"" +
	 "  }," +
	 "  \"config\": {" +
	 "    \"longname\":    \"configuration\"," +
	 "    \"ucfirstname\": \"Configuration\"," +
	 "    \"tooltip\":     \"Configuration node\"" +
	 "  }," +
	 "  \"division\": {" +
	 "    \"longname\":    \"division\"," +
	 "    \"ucfirstname\": \"Division\"," +
	 "    \"tooltip\":     \"Division node\"" +
	 "  }," +
	 "  \"ignore\": {" +
	 "    \"longname\":    \"ignore\"," +
	 "    \"ucfirstname\": \"Ignore\"," +
	 "    \"tooltip\":     \"Ignore-list entry node\"" +
	 "  }," +
	 "  \"line\": {" +
	 "    \"longname\":    \"line\"," +
	 "    \"ucfirstname\": \"Line\"," +
	 "    \"tooltip\":     \"Report-line node\"" +
	 "  }," +
	 "  \"lines\": {" +
	 "    \"longname\":    \"lines\"," +
	 "    \"ucfirstname\": \"Lines\"," +
	 "    \"tooltip\":     \"Report-lines node\"" +
	 "  }," +
	 "  \"list\": {" +
	 "    \"longname\":    \"list\"," +
	 "    \"ucfirstname\": \"List\"," +
	 "    \"tooltip\":     \"Ignore-list node\"" +
	 "  }," +
	 "  \"lists\": {" +
	 "    \"longname\":    \"lists\"," +
	 "    \"ucfirstname\": \"Lists\"," +
	 "    \"tooltip\":     \"Ignore-lists node\"" +
	 "  }," +
	 "  \"mod\": {" +
	 "    \"longname\":    \"modification\"," +
	 "    \"ucfirstname\": \"Modification\"," +
	 "    \"tooltip\":     \"Modification node\"" +
	 "  }," +
	 "  \"report\": {" +
	 "    \"longname\":    \"report\"," +
	 "    \"ucfirstname\": \"Report\"," +
	 "    \"tooltip\":     \"Report node\"" +
	 "  }," +
	 "  \"reports\": {" +
	 "    \"longname\":    \"reports\"," +
	 "    \"ucfirstname\": \"Reports\"," +
	 "    \"tooltip\":     \"Reports node\"" +
	 "  }," +
	 "  \"rules\": {" +
	 "    \"longname\":    \"rules\"," +
	 "    \"ucfirstname\": \"Rules\"," +
	 "    \"tooltip\":     \"Rules node\"" +
	 "  }," +
	 "  \"site\": {" +
	 "    \"longname\":    \"site\"," +
	 "    \"ucfirstname\": \"Site\"," +
	 "    \"tooltip\":     \"Site node\"" +
	 "  }," +
	 "  \"store\": {" +
	 "    \"longname\":    \"store\"," +
	 "    \"ucfirstname\": \"Store\"," +
	 "    \"tooltip\":     \"Stored value node\"" +
	 "  }," +
	 "  \"top\": {" +
	 "    \"longname\":    \"top\"," +
	 "    \"ucfirstname\": \"Top\"," +
	 "	  \"tooltip\":     \"Top node of the node tree\"" +
	 "  }" + 
	 "}";
  /* Build JSON elements (actually JSON elements within JSON elements) from each of
     the JSON strings */ 
  static JsonParser   HDLmModInfoDataParser = new JsonParser();  
  static JsonElement  HDLmModInfoData = HDLmModInfoDataParser.parse(HDLmModInfoDataString);
  static JsonParser   HDLmModTypeInfoParser = new JsonParser();  
  static JsonElement  HDLmModTypeInfo = HDLmModTypeInfoParser.parse(HDLmModTypeInfoString);
  static JsonParser   HDLmModTreeInfoParser = new JsonParser();  
  static JsonElement  HDLmModTreeInfo = HDLmModTreeInfoParser.parse(HDLmModTreeInfoString); 
	/* All instances of the HDLmMod class have a standard set of fields */
  @SerializedName("find")
	private ArrayList<HDLmFind>   finds = null;
	private ArrayList<String>     values = null;
	private Boolean               enabled = false;
	/* The next field is used to save the number of errors that
	   were found building the current modification. If this value
	   is greater than zero, then the modification can not be used.
	   Note that the enabled field is not generally changed even
	   when the error count is greater than zero. The exception
	   is that a copy of the enabled field is changed when a copy
	   of the current modification is built.
	   
	   The transient attribute is used below to prevent JSON 
	   serialization of this field */
	private transient int   errorCount = 0;
	/* We assume that the path value is not a regex. However, if the path value 
	   actually is a regex, the regex flag below is set to true. Note that most 
	   of the work of actually matching path values is done with the match object. 
	   The field below is used more for communication with JavaScript. */
  private Boolean         pathre = false;
	private HDLmModTypes    type = HDLmModTypes.NONE;
	/* The transient attribute is used below to prevent JSON serialization
     of this field. Note that special case code is used to add this value
     to the JSON used for rules. */
	private transient int   valuesCount = 0;
	/* Parameter number is handled as a reference so that it can be set to a null
	   value. If the value is actually set, it must be a positive or zero integer
	   (zero is allowed). */
	@SerializedName("parameter")
	private Integer         parameter = null;
	@SerializedName("cssselector")
	private String          cssSelector = null;
	private String          comments = null;
	/* The next two fields are part of every modification. Of course, they are 
	   part of all objects that are created by extending this class. The created
	   field shows when an object was created. The lastModified field shows when
	   the object (or a nested object) was last modified. */
  protected Instant       created = null;
  protected Instant       lastModified = null;
	/* The dummy created field is only used for JSON serialization. This field
     is not used or set, otherwise. */
  private String          dummyCreated = null;
  /* The dummy last modified field is only used for JSON serialization. This field
     is not used or set, otherwise. */
  private String          dummyLastModified = null;
	/* The dummy pass-through field is only used for JSON serialization. This field
     is not used or set, otherwise. */
  @SerializedName("passThru")
  private Boolean         dummyPassThru = null;
	/* The dummy table field is only used for JSON serialization. This field
     is not used or set, otherwise. */
	private String          dummyTable = null;
	/* The dummy type field is only used for JSON serialization. This field
     is not used or set, otherwise. */
  /* @SerializedName("type") */
  private String          dummyType = null;
	/* The dummy updated field is only used for JSON serialization. This field
	   is not used or set, otherwise. */
	@SerializedName("updated")
	private Boolean         dummyUpdated = null;
	private String          extra = null;
	private String          name = null;
	@SerializedName("nodeiden")
  private HDLmNodeIden    nodeIden = null;
	/* We assume that the path value is not a regex. However, if the path value 
	   actually is a regex, the regex flag above is set to true. Note that most
	   of the work of actually matching path values is done with the match object.
	   The field below is used more for communication with JavaScript. */
	@SerializedName("path")
  private String          pathValue = null;
	@SerializedName("usemode")
	private String          useMode = null;
	private String          value = null;
	private String          valueSuffix = null;
	@SerializedName("xpath")
	private String          xPath = null;
	/* A reference to the path value match object is stored below. The path 
	   value match object has methods that handle all types of matching. The
	   match object may specify a simple string comparison, a regex match, 
	   a glob match, or a like (SQL LIKE) match. 
	   
	   The transient attribute is used below to prevent JSON serialization
	   of this object. The JavaScript code can not use the match object.
	   As such, there is no point in serializing it. */
  private transient HDLmMatch   pathMatch = null;	
	/* This is the default constructor for this class. It doesn't do anything.
	   All fields of this class will be set to the default values specified 
	   above. This constructor is required so that this class can be extended. */
	protected HDLmMod() {}
	/* This constructor copies all of the fields from another modification instance.
	   Note that the copy is a deep copy of sorts. All of the fields should actually
	   be copied or should be immutable. */
	protected HDLmMod(HDLmMod oldMod) {
		/* Check if the old modification passed to this routine is null or not */
		if (oldMod == null) {
			String  errorText = "Old details reference used to build modification details is null";
			throw new NullPointerException(errorText);
		}
		/* The call below copies the fields from the old modification to
		   the new modification. In other words, the actual work of a copy
		   constructor is done by the call below. */ 
		copyModFields(oldMod);
	}
	/* This is a constructor for the HTML modifications class. It must be passed a
	   JSON element that contains all of the details of the modification. */
	protected HDLmMod(JsonElement jsonElement) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build modification details is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few fields for use below */
		String  jsonFieldName;
		/* Set the error count to zero. The error count is incremented each time an
		   error is detected. If the final error count (for the current modification) is
		   greater than zero, the current modification object is disabled (the enabled
		   field is set false). Note that a reference is used below so that the error
		   count can be updated by the routines called using error count.*/
		MutableInt errors = new MutableInt(0);
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Get the list of keywords and values in the JSON object */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element used to build modification is JSON null");
		}
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		Set<String>   jsonKeys = jsonObject.keySet();
		/* This routine can be passed a tree node for an actual modification (rule) 
		   or just a value. We must handle both cases here. */ 
	  if (!jsonKeys.contains("type")) {
	    HDLmAssertAction(false, "JSON element does not contain a type field");
		}
	  /* Try to get the JSON element with the type */
	  JsonElement   jsonTypeElement = jsonObject.get("type");
	  if (jsonTypeElement.isJsonNull()) {
	    HDLmAssertAction(false, "JSON type element is JSON null");
	  }
		/* Set the class instance variables */
		this.name = HDLmMod.modFieldString(editorType, errors, 
				                               jsonObject, jsonKeys, 
				                               "name", 
				                               HDLmWhiteSpace.WHITESPACENOTOK,
				                               HDLmReportErrors.REPORTERRORS,
				                               HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		/* Get the path value string and check if it is really a regex. We treat the path 
		   value string as a regex, if the first, second and last characters are forward
		   slashes. Note that the first actual character will always be a forward slash.
		   The first actual path value string character must be a forward slash. */
		jsonFieldName = "pathValue";
		if (jsonObject.has("pathvalue"))
			jsonFieldName = "pathvalue";
		else if (jsonObject.has("path"))
			jsonFieldName = "path";	
		String  pathValueString  = HDLmMod.modFieldString(editorType, errors, 
							                                        jsonObject, jsonKeys, 
							                                        jsonFieldName,
							                                        HDLmWhiteSpace.WHITESPACENOTOK,
							                                        HDLmReportErrors.REPORTERRORS,
							                                        HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		this.pathValue = pathValueString; 
		/* Use the path value string (if any) to build the path value match object. 
		   The path value match object supports all of the different types of matching. */ 
		if (pathValueString != null) {
			/* Check if the path value string is valid or not. We don't want 
			   to try to build a match object using an invalid path value string 
			   (match string). */ 
			String  valid = HDLmMatch.check(pathValueString);
			if (valid != null) {
				String  errorText = valid;
				HDLmMod.reportError(editorType, errors, 
						                jsonObject, errorText, 
						                36, HDLmReportErrors.REPORTERRORS);				
			}
			else
			  this.pathMatch = new HDLmMatch(pathValueString);
		}
		/* Get a few fields from the JSON object */
		Boolean   enabledBoolean = HDLmMod.modFieldBoolean(editorType, errors, 
				                                               jsonObject, jsonKeys, 
				                                               "enabled");
		if (enabledBoolean != null)
		  this.enabled = enabledBoolean;
		/* Get a few fields from the JSON object */
		jsonFieldName = "useMode";
		if (jsonObject.has("usemode"))
			jsonFieldName = "usemode";
		else if (jsonObject.has("use"))
			jsonFieldName = "use";	
		/* Check if any JSON element exists for any variation of 'useMode'
		   (without the quotes). UseMode (actually the first letter is 
		   lower case) is quite optional. If 'useMode' (or some other 
		   variant) is not found, we don't want to increment the error 
		   counter or generate any error messages. */ 		
		if (jsonObject.has(jsonFieldName)) {
			String  useModeString = HDLmMod.modFieldString(editorType, errors, 
					                                           jsonObject, jsonKeys, 
					                                           jsonFieldName,
					                                           HDLmWhiteSpace.WHITESPACENOTOK, 
					                                           HDLmReportErrors.REPORTERRORS,
					                                           HDLmZeroLengthOk.ZEROLENGTHOK);
			if (useModeString != null)
			  this.useMode = useModeString;
		}
		/* Try to get the comments value from the JSON element. Note that
		   the call below will never report an error unless the comments
		   field is actually missing from the JSON. This does not appear
		   to be true in any actual case. Note that the comments are
		   optional. Skip this code is we don't have any comments. */
		if (HDLmJson.hasJsonKey(jsonObject, "comments")) { 
		  String commentsInfo = HDLmMod.modFieldString(editorType, errors, 
			  	                                         jsonObject, jsonKeys, 
				                                           "comments", 
				                                           HDLmWhiteSpace.WHITESPACEOK, 
				                                           HDLmReportErrors.REPORTERRORS,
				                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);			 
		  if (commentsInfo != null && !StringUtils.isWhitespace(commentsInfo)) {
			  this.comments = commentsInfo;
		  }
		}
		/* Try to get the extra information value from the JSON element.
		   Note that the call below will never report an error unless the
		   extra information field is actually missing from the JSON.
		   This does not appear to be true in any actual case. */
		String  extraInfo = HDLmMod.modFieldString(editorType, errors, 
				                                       jsonObject, jsonKeys, 
				                                       "extra", 
				                                       HDLmWhiteSpace.WHITESPACEOK, 
				                                       HDLmReportErrors.REPORTERRORS,
				                                       HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (extraInfo != null && !StringUtils.isWhitespace(extraInfo)) {
			this.extra = extraInfo;
		}
		/* Try to get the created time-date from the JSON element.
	     Note that the call below will never report an error unless the
	     created date-time field is actually missing from the JSON.
	     This does not appear to be true in any actual case. */
		Instant   createdInfo = HDLmMod.modFieldDateTime(editorType, errors, 
								                                     jsonObject, jsonKeys, 
								                                     "created",  
								                                     HDLmReportErrors.REPORTERRORS);
		if (createdInfo != null) {
			this.created = createdInfo;
	  }
		else 
			this.created = Instant.now();
		/* Try to get the last modified time-date from the JSON element.
       Note that the call below will never report an error unless the
       last modified date-time field is actually missing from the JSON.
       This does not appear to be true in any actual case. */
		Instant   lastModifiedInfo = HDLmMod.modFieldDateTime(editorType, errors, 
								                                          jsonObject, jsonKeys, 
								                                          "lastmodified",  
								                                          HDLmReportErrors.REPORTERRORS);
		if (lastModifiedInfo != null) {
			this.lastModified = lastModifiedInfo;
	  }
		else 
			this.lastModified = Instant.now();
		/* Check if we have CSS Selector information, XPath search information, or if we
		   have find information. All are supported. We give preference to CSS Selector
		   information, then XPath search information, and finally to find information. */
		String cssInfo = HDLmMod.modFieldString(editorType, errors, 
				                                    jsonObject, jsonKeys, 
				                                    "cssselector", 
				                                    HDLmWhiteSpace.WHITESPACEOK,
				                                    HDLmReportErrors.REPORTERRORS,
				                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (cssInfo != null && !StringUtils.isWhitespace(cssInfo)) {
			this.cssSelector = cssInfo;
		}
		/* Check for possible XPath search information */
		String xpathInfo = HDLmMod.modFieldString(editorType, errors, 
				                                      jsonObject, jsonKeys, 
				                                      "xpath", 
				                                      HDLmWhiteSpace.WHITESPACEOK, 
				                                      HDLmReportErrors.REPORTERRORS,
				                                      HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (xpathInfo != null && !StringUtils.isWhitespace(xpathInfo)) {
			this.xPath = xpathInfo;
		}
		/* Get some values for locating what needs to be changed. The find criteria may
		   specify one level or multiple levels. */
		ArrayList<HDLmFind> findInfo = HDLmMod.modFieldFindArray(editorType, errors, 
				                                                     jsonObject, jsonKeys,
				                                                     "find");
		if (findInfo != null) {
			this.finds = findInfo;
		}
		/* Get some node identifier values, if possible */
		HDLmNodeIden  nodeIden = HDLmMod.modFieldNodeIden(editorType, errors, 
                                                      jsonObject, jsonKeys,
                                                      "nodeiden");
		if (nodeIden != null && nodeIden.isUsable()) {
			this.nodeIden = nodeIden;
		}
		/* Get the modification type */
		this.type = HDLmMod.modFieldModType(editorType, errors, 
				                                jsonObject, jsonKeys, 
				                                "type");
		/* Make sure that at least one type of locate information was provided. 
		   No type of locate information is needed or allowed for visit rules.
		   The locate information can be provided using a CSS select, or an XPath
		   string, or a set of node identifier values, or a set of finds. One of
		   these must be provided. They can not all be empty except for visit 
		   rules, for which they must all be empty.  */
		HDLmMod.modLocateCheck(editorType, 
				                   this.type,
				                   errors, 
				                   jsonObject, cssInfo, 
				                   xpathInfo, findInfo,
				                   nodeIden);
		/* Set the parameter number, if it was provided */
		if (jsonKeys.contains("parameter"))
			this.parameter = HDLmMod.modFieldInteger(editorType, errors, 
					                                     jsonObject, jsonKeys,
					                                     "parameter");		
		/* Generally we have to obtain a parameter number for each modification.
		   However, for a few specific modification types, this is not correct. These
		   modification types are used to return information that does not depend on 
		   a parameter number, to a server. */	
		if (this.type == HDLmModTypes.ATTRIBUTE || 
	      this.type == HDLmModTypes.EXTRACT   ||
		    this.type == HDLmModTypes.MODIFY    ||
				this.type == HDLmModTypes.NOTIFY) {
			if (this.parameter != null) {
				String valueString = this.parameter.toString();
				HDLmMod.reportErrorValue(editorType, errors, 
						                     jsonObject, "parameter", 
						                     valueString, 
						                     "Modification parameter number not allowed", 
						                     2, 
						                     HDLmReportErrors.REPORTERRORS);
			}
		}
    /* For all other modification types the parameter number is required.
       Report an error, if we don't have a parameter number. */
		else {			
			if (this.parameter == null) {
				HDLmMod.reportErrorNoValue(editorType, errors, 
            jsonObject, "parameter", 
            "Modification JSON missing field", 
            3, HDLmReportErrors.REPORTERRORS);
			}
		}
		/* Handle each type of modification. Some modification specific code is needed
		   for each type of modification. */
		switch (this.type) {
			/* Handle an attribute change */
			case ATTRIBUTE: {
				/* Try to get the extra information value from the JSON element.
				   Note that an actual extra information value is required here.
				   A zero-length value or a value of blanks is not OK. */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACENOTOK, 
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				break;
			}
			/* Handle a change attributes modification (probably a DOM attributes modification) */
			case CHANGEATTRS: {
				/* LOG.info("In HDLmMod.<init>"); */
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "changeattrsvalues");
				/* Check each of the values and make sure it is a valid change attributes value */
				if (this.values != null)
				  HDLmMod.modFieldStringArrayChangeAttrs(this.values,
                                                 editorType, 
						                                     errors, 
				                                         jsonObject, 
						                                     name, 
						                                     HDLmReportErrors.REPORTERRORS);			
				Integer arraySize = null;
				if (this.values != null)
		  		arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
				                                                 jsonObject, "changeattrsvalues", 
				                                                 arraySize,
				                                                 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
			  	this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle some type of a change nodes operation */
			case CHANGENODES: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "changenodesvalues");
				/* Check each of the values and make sure it is a valid change attributes value */
				if (this.values != null) {
					/*
			  	HDLmMod.modFieldStringArrayChangeNodes(this.values,
				                                         editorType, 
		                               		           errors, 
				                                         jsonObject, 
		                              		           name, 
		                               		           HDLmReportErrors.REPORTERRORS);	
		      */                         		            	
				}
        Integer arraySize = null;
        if (this.values != null)
          arraySize = this.values.size();
        Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
                                                         jsonObject, "changenodesvalues", 
                                                         arraySize,
                                                         HDLmZeroLengthOk.ZEROLENGTHNOTOK);
        if (valuesCountLocal != null)
          this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle some type of an extract operation */
			case EXTRACT: {
				/* Try to get the extra information value from the JSON element.
				   Note that an actual extra information value is not required here.
				   A zero-length value or a value of blanks is OK. A default value
				   will be used, if an actual value is not provided. */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACEOK,
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				break;
			}
			/* Handle style font colors */
			case FONTCOLOR: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "colors");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, 
	                                                       errors,					
	                                                       jsonObject, 
	                                                       "colors", 
	                                                       arraySize, 
	                                                       HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle style font family. This is something like "Arial, Helvetica,
			   sans-serif". Note that "Arial, Helvetica, sans-serif" is just one font
			   family. */
			case FONTFAMILY: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "families");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
						                                             jsonObject, "families", 
						                                             arraySize,
						                                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle style font kerning. The values are things like "auto", "normal" or
			   "none". */
			case FONTKERNING: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "kernings");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
						                                             jsonObject, "kernings", 
						                                             arraySize,
						                                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle style font size. The style font size values may be specified with 
			   a percentage suffix (as in 150%) or a pixel suffix (as in 25px) or with no
			   suffix at all. If no suffix is specified, than a pixel value is assumed. */
			case FONTSIZE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "sizes");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "sizes", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK,
										                                     HDLmReportErrors.REPORTERRORS);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				/* Check if an array of values was found. We can't do anymore checking
				   if no array of values was found. */
				if (this.values == null)
					break;			
	      /* Check each of the font size values */
				String   forceSelectString = HDLmDefines.getString("HDLMFORCEVALUE");
		    if (forceSelectString == null) {
			    String   errorFormat = "Define value for force select not found (%s)";
			    String   errorText = String.format(errorFormat, "HDLMFORCEVALUE");
		 	    HDLmAssertAction(false, errorText);		    	
		    }
				/* Build a list of acceptable suffix values */
				ArrayList<String>  fontSuffixValues = new ArrayList<String>(
				  List.of("px", "em", "%"));
	      for (String fontSize : this.values) {
	        /* The value obtained above must be split into a base numeric value
	           and a suffix value. The suffix is either a percent sign or a set
	           of two letter suffixes (as in 'px'). */
	        ArrayList<String>  fontSizeArray = HDLmMod.modPercentPixel(fontSize);
	        if (fontSizeArray.size() >= 1) {
	          String   fontValue = fontSizeArray.get(0);
	          String   fontValueLocal = fontValue;
	          /* Remove the special value prefix, if need be */
	          if (fontValue.startsWith(forceSelectString)) 
	            fontValueLocal = fontValue.substring(forceSelectString.length());
	          Integer  fontValueInteger = HDLmUtility.convertInteger(fontValueLocal);
	          HDLmMod.rangeField(editorType, errors, 
	          		               jsonObject, "font size", 
	          		               fontValueInteger,
	          		               HDLmDefines.getNumber("HDLMFONTSIZEMIN"),
	          		               HDLmDefines.getNumber("HDLMFONTSIZEMAX"));
	        }
	        /* Handle (check) the suffix value if we have one */
	        if (fontSizeArray.size() >= 2) {
	          String   fontValueSuffix = fontSizeArray.get(1);
	          /* Check if the suffix value */
	          if (!fontValueSuffix.equals(""))
	            HDLmMod.modSuffixCheck(editorType, errors, 
	            		                   jsonObject, "font size", 
	                                   fontSuffixValues, fontValueSuffix);                                   
	        }
	      }
	      break;			
			}
			/* Handle style font style. The values are things like "normal", "italic" or
			   "oblique". */
			case FONTSTYLE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "styles");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "styles", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle style font weight. The values are things like "normal", "lighter",
			   "bold" or 900. */
			case FONTWEIGHT: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "weights");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "weights", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a height of some kind. The height values may be specified with a
			   percentage suffix (as in 150%) or a pixel suffix (as in 25px) or with no
			   suffix at all. If no suffix is specified, than a pixel value is assumed. Note
			   that the height value can be specified as "auto". If a height value of "auto"
			   is specified, then the aspect ratio will be preserved, if possible. */
			case HEIGHT: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "heights");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "heights", 
										                                     arraySize, 
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK,
										                                     HDLmReportErrors.REPORTERRORS);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
	      /* Check if an array of values was found. We can't do anymore checking
	         if no array of values was found. */
	      if (this.values == null)
	        break;	
	      /* Check each of the height values */
				String   forceSelectString = HDLmDefines.getString("HDLMFORCEVALUE");
				/* Build a list of acceptable suffix values */
				ArrayList<String>  heightSuffixValues = new ArrayList<String>(
				  List.of("px", "em", "%"));
	      for (String height : this.values) {
	        /* The value obtained above must be split into a base numeric value
	           and a suffix value. The suffix is either a percent sign or a set
	           of two letter suffixes (as in 'px'). */
	        ArrayList<String>  heightArray = HDLmMod.modPercentPixel(height);
	        if (heightArray.size() >= 1) {
	          String   heightValue = heightArray.get(0);
	          String   heightValueLocal = heightValue;
	          /* Remove the special value prefix, if need be */
	          if (heightValue.startsWith(forceSelectString)) 
	            heightValueLocal = heightValue.substring(forceSelectString.length());
	          if (!heightValueLocal.equals("auto")) {
	          Integer  heightValueInteger = HDLmUtility.convertInteger(heightValueLocal);
	          HDLmMod.rangeField(editorType, errors, 
	          		               jsonObject, "height", 
	          		               heightValueInteger,
	          		               HDLmDefines.getNumber("HDLMHEIGHTMIN"),
	          		               HDLmDefines.getNumber("HDLMHEIGHTMAX"));
	          }
	        }
	        /* Handle (check) the suffix value if we have one */
	        if (heightArray.size() >= 2) {
	          String   heightValueSuffix = heightArray.get(1);
	          /* Check if the suffix value */
	          if (!heightValueSuffix.equals(""))
	            HDLmMod.modSuffixCheck(editorType, errors, 
	            		                   jsonObject, "height", 
	                                   heightSuffixValues, 
	                                   heightValueSuffix);                                   
	        }
	      }
	      break;		
			}
			/* Handle images. Images can be many types of image files. */
		  case IMAGE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "images");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
						                                             jsonObject, "images", 
						                                             arraySize,
						                                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
		  }
			/* Handle a modification (probably a DOM modification) */
			case MODIFY: {
				/* Try to get the extra information value from the JSON element */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACENOTOK,
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				break;
			}
			/* Handle some type of a notification */
			case NOTIFY: {
				/* Try to get the extra information value from the JSON element */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACEOK,
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "targets");
				/* The notify modification type supports (but does not require) one more 
				   extract targets. The extract targets (if any) must be processed here. */
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "targets", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Change (possibly) the order of a set of subnodes */
			case ORDER: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "orders");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "orders", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a remove modification (probably a DOM modification) */
			case REMOVE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "removevalues");
				/* Check each of the values and make sure it is a valid remove value */
				if (this.values != null)
				  HDLmMod.modFieldStringArrayRemove(this.values,
                                            editorType, 
						                                errors, 
				                                    jsonObject, 
						                                name, 
						                                HDLmReportErrors.REPORTERRORS);			
				Integer arraySize = null;
				if (this.values != null)
		  		arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
				                                                 jsonObject, "removevalues", 
				                                                 arraySize,
				                                                 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
			  	this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a replace modification (probably a DOM modification) */
			case REPLACE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "replacevalues");
				/* Check each of the values and make sure it is a valid replace value */
				if (this.values != null)
				  HDLmMod.modFieldStringArrayReplace(this.values,
                                             editorType, 
						                                 errors, 
				                                     jsonObject, 
						                                 name, 
						                                 HDLmReportErrors.REPORTERRORS);			
				Integer arraySize = null;
				if (this.values != null)
		  		arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
				                                                 jsonObject, "replacevalues", 
				                                                 arraySize,
				                                                 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
			  	this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a script modification */
			case SCRIPT: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "scripts");
				/* Check each of the values and make sure it is a valid script value */
				if (this.values != null)
				  HDLmMod.modFieldStringArrayScript(this.values,
                                            editorType, 
						                                errors, 
				                                    jsonObject, 
						                                name, 
						                                HDLmReportErrors.REPORTERRORS);			
				Integer arraySize = null;
				if (this.values != null)
		  		arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
				                                                 jsonObject, "scripts", 
				                                                 arraySize,
				                                                 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
			  	this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle style modifications */
			case STYLE: {
				/* Try to get the extra information value from the JSON element */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACENOTOK, 
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "styles");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "styles", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle text replacement */
			case TEXT: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "newtexts");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "newtexts", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle text replacement with original text checking */
			case TEXTCHECKED: {
				/* Try to get the extra information value from the JSON element */
				String extraInfoLocal = HDLmMod.modFieldString(editorType, errors, 
						                                           jsonObject, jsonKeys, 
						                                           "extra", 
						                                           HDLmWhiteSpace.WHITESPACENOTOK, 
						                                           HDLmReportErrors.REPORTERRORS,
						                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "newtexts");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "newtexts", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle replacing titles (for buttons and the like) */
			case TITLE: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "titles");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "titles", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);		
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a visit modification (definitely not a DOM modification) */
			case VISIT: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
                                                  jsonObject, jsonKeys, 
                                                  "visitvalues");
				/* Check each of the values and make sure it is a valid visit value */
				if (this.values != null)
				  HDLmMod.modFieldStringArrayVisit(this.values,
                                           editorType, 
						                               errors, 
				                                   jsonObject, 
						                               name, 
						                               HDLmReportErrors.REPORTERRORS);			
				Integer arraySize = null;
				if (this.values != null)
		  		arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
				                                                 jsonObject, "visitvalues", 
				                                                 arraySize,
				                                                 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (valuesCountLocal != null)
			  	this.valuesCount = valuesCountLocal;
				break;
			}
			/* Handle a width of some kind. The width values may be specified with a
			   percentage suffix (as in 150%) or a pixel suffix (as in 25px) or with no
			   suffix at all. If no suffix is specified, than a pixel value is assumed. */
			case WIDTH: {
				this.values = HDLmMod.modFieldStringArray(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "widths");
				Integer arraySize = null;
				if (this.values != null)
					arraySize = this.values.size();
				Integer valuesCountLocal = HDLmMod.modArrayCheck(editorType, errors, 
										                                     jsonObject, "widths", 
										                                     arraySize,
										                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK,
										                                     HDLmReportErrors.REPORTERRORS);
				if (valuesCountLocal != null)
					this.valuesCount = valuesCountLocal;
	      /* Check if an array of values was found. We can't do anymore checking
	         if no array of values was found. */
	      if (this.values == null)
	        break;	
	      /* Check each of the width values */
				String   forceSelectString = HDLmDefines.getString("HDLMFORCEVALUE");
				/* Build a list of acceptable suffix values */
				ArrayList<String>  widthSuffixValues = new ArrayList<String>(
				  List.of("px", "em", "%"));
	      for (String width : this.values) {
	        /* The value obtained above must be split into a base numeric value
	           and a suffix value. The suffix is either a percent sign or a set
	           of two letter suffixes (as in 'px'). */
	        ArrayList<String>  widthArray = HDLmMod.modPercentPixel(width);
	        if (widthArray.size() >= 1) {
	          String   widthValue = widthArray.get(0);
	          String   widthValueLocal = widthValue;
	          /* Remove the special value prefix, if need be */
	          if (widthValue.startsWith(forceSelectString)) 
	            widthValueLocal = widthValue.substring(forceSelectString.length());
	          if (!widthValueLocal.equals("auto")) {
	          Integer  widthValueInteger = HDLmUtility.convertInteger(widthValueLocal);
	          HDLmMod.rangeField(editorType, errors, 
	          		               jsonObject, "width", 
	          		               widthValueInteger,
	          		               HDLmDefines.getNumber("HDLMWIDTHMIN"),
	          		               HDLmDefines.getNumber("HDLMWIDTHMAX"));
	          }
	        }
	        /* Handle (check) the suffix value if we have one */
	        if (widthArray.size() >= 2) {
	          String   widthValueSuffix = widthArray.get(1);
	          /* Check if the suffix value */
	          if (!widthValueSuffix.equals(""))
	            HDLmMod.modSuffixCheck(editorType, errors, 
	            		                   jsonObject, "width", 
	                                   widthSuffixValues, 
	                                   widthValueSuffix);                                   
	        }
	      }
	      break;					
			}
			/* Report an error if the type value did not match. This step is skipped if the
			   type is null. Of course, null will not match and an error has already been
			   reported in this case. */
			default: {
				if (this.type != null) {
					String localString = this.type.toString();
					HDLmMod.reportField(editorType, errors, 
							                jsonObject, "type", 
							                localString);
				}
			}
    } 
		/* Set the error count to the number of errors that were actually found
		   building the current modification. If this count is greater than zero,
		   than the current modification can not really be used. Note that only
		   the error count field is set here. The enabled field is not changed
		   here, based on the number of errors. */
    if (errors.intValue() > 0)
		  this.errorCount = errors.intValue();
  }
	/* Apply (if possible) an HTML modification to a web page. This method returns
	   true if an actual change was mode. This method returns false if no actual
	   change was made.
	   
	   Note that the DOM is passed by reference here. This is required because the
	   DOM is actually modified in this code. The modified DOM must be available to
	   the caller for use later.
	   
	   This routine assumes that each modification object is valid and complete. Of
	   course, this may not always be true. The modification constructor will always
	   set the enabled field to false, if any errors are detected constructing a
	   modification object. */
	protected boolean apply(Document htmlDom, String pathValueStr, ArrayList<Double> parametersArray,
		                   	  ArrayList<HDLmSavedChange> savedChangesArray) {
		if (htmlDom == null) {
			String  errorText = "HTML DOM passed to apply is null";
			throw new NullPointerException(errorText);
		}
		if (pathValueStr == null) {
			String  errorText = "Path value string passed to apply is null";
			throw new NullPointerException(errorText);
		}
		if (parametersArray == null) {
			String  errorText = "Parameters array passed to apply is null";
			throw new NullPointerException(errorText);
		}
		if (savedChangesArray == null) {
			String  errorText = "Saved changes array passed to apply is null";
			throw new NullPointerException(errorText);
		}
		HDLmEditorTypes   editorType = HDLmEditorTypes.PASS;
		/* Assume that no match was found for this modification. This could happen for
		   any number of reasons. The path value string may not match the current modification.
		   The current modification may not be enabled (it may be disabled). Of course,
		   the required tag may not be found in the DOM. */
		boolean matchFound = false;
		String matchError = "";
		/* Get a few values */
		HDLmModTypes modType = this.getType();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Skip the current HTML page if need be. This flag is set to false if any
			   errors are detected building the modification object. */
			if (this.enabled != true) {
				matchError = "disabled";
				break;
			}
			/* Each modification objects has a match object. The check method 
			   of the match object can be used to check for a path value match 
			   or not. */ 
		  boolean   match = this.getPathValueMatch().match(pathValueStr);
			if (match == false) {
				matchError = "Path value mismatch";
				break;
			}
			/* Build an associative array for converting modification type names to style
			   font contents names */
			Map<String, String> fontNames = Map.ofEntries(
					Map.entry("FONTCOLOR", "color"),
					Map.entry("FONTFAMILY", "font-family"), 
					Map.entry("FONTKERNING", "font-kerning"),
					Map.entry("FONTSIZE", "font-size"),
					Map.entry("FONTSTYLE", "font-style"), 
					Map.entry("FONTWEIGHT", "font-weight"));
			/* Get a few values */
			Integer parameterNumber = this.parameter;
			Double parameterValue = 0.0;
			if (parameterNumber != null && parameterNumber >= 0 && parameterNumber < parametersArray.size())
				parameterValue = parametersArray.get(parameterNumber);
			switch (modType) {
				/* Handle an attribute change */
				case ATTRIBUTE: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText, newText);
						break;
					}
					break;
				}
				/* Handle some type of an extract operation */
				case EXTRACT: {
					/* Find the place in the HTML where we need to add the new hidden input node */
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						/* Build the new hidden input node */
						Element newNode = htmlDom.createElement("input");
						newNode.attr("type", "hidden");
						newNode.attr("name", HDLmDefines.getString("HDLMPOSTDATA"));
						if (1 == 2) {
							Elements subs = node.children();
							Element sub = subs.get(0);
							sub.before(newNode);
						}
						node.appendChild(newNode);
						break;
					}
					break;
				}
				/* Handle a set of style font related changes */
				case FONTCOLOR:
				case FONTFAMILY:
				case FONTKERNING:
					/* Handle style font size. This selected value may either be a percentage
					   or a number of pixels. The code is the same for both cases. The standard 
					   apply style code is used in this case. The apply style code assumes that
					   the font-text      will be changed, modified, or set. This code changes or modifies the
				     font-size will be changed, modified, or set. This code also uses a
			  	   multiplication to get the final parameter value. The standard apply style
				     code, picks a value from a list of values. */
				case FONTSIZE:
				case FONTSTYLE:
				case FONTWEIGHT: {
					String newName = fontNames.get(modType.toString());
					if (this.applyStyle(savedChangesArray, htmlDom, 
							                modType, newName, parameterNumber, 
							                parameterValue) == true)
						matchFound = true;
					break;
				}			
				/* Handle height and width values. The values may either be a percentage or a
				   number of pixels. The code is the same for both cases. Note that the value
				   may actually be the string "auto". The string "auto" can not be adjusted
				   using a parameter value. */
				case HEIGHT:
				case WIDTH: {
					String modTypeString = modType.toString().toLowerCase();
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = null;
						if (node.hasAttr(modTypeString))
							oldText = node.attr(modTypeString);
						node.attr(modTypeString, newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText,	newText);
						break;
					}
					break;	  
				}
				/* Handle a modification (probably a DOM modification) */
				case MODIFY: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText, newText);
						break;
					}
					break;
				}
				/* Handle some type of a notification */
				case NOTIFY: {
					/* Find the place in the HTML where we need to add the new hidden input node */
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						/* Build the new hidden input node */
						Element newNode = htmlDom.createElement("input");
						newNode.attr("type", "hidden");
						newNode.attr("name", HDLmDefines.getString("HDLMPOSTDATA"));
						if (1 == 2) {
							Elements subs = node.children();
							Element sub = subs.get(0);
							sub.before(newNode);
						}
						node.appendChild(newNode);
						break;
					}
					break;
				}
				/* Change (possibly) the order of a set of subnodes */
				case ORDER: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText, newText);
						break;
					}
					break;
				}
				/* Handle style modifications */
				case STYLE: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText, newText);
						break;
					}
					break;
				}
				/* Handle text replacement (for buttons and the like) */
				case TEXT: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText,	newText);
						break;
					}
					break;
				}
				/* Handle text replacement with checking (for buttons and the like) */
				case TEXTCHECKED: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, 
								                this.name, this.getPathMatchValue(), modType, oldText,	newText);
						break;
					}
					break;
				}
				/* Handle title replacement (for buttons and the like) */
				case TITLE: {
					ArrayList<String> newTexts = this.getValues();
					int newCount = this.getValuesCount();
					int parameterIndex = (int) (newCount * parameterValue);
					parameterIndex = Math.min(parameterIndex, newCount - 1);
					String newText = newTexts.get(parameterIndex);
					Elements nodeList = this.find(htmlDom);
					for (Element node : nodeList) {
						matchFound = true;
						String oldText = node.text();
						node.text(newText);
						HDLmMod.saveChanges(savedChangesArray, 
								                parameterNumber, 
								                parameterValue, 
								                this.name, this.getPathMatchValue(), 
								                modType, oldText, newText);
						break;
					}
					break;
				}
				/* Throw an exception if the type did not match. This should not happen because
				   we checked the type as part of building the modification object. */
				default: {
	        String  errorText = "Invalid modification type value - " + modType.toString();
	        HDLmAssertAction(false, errorText);
				}
		  }
			/* Check if a match was found above. Report an error if no match was found. */
			if (matchFound == false)
				matchError = "nomatch";
			break;
		}
		/* Check if a matching DOM node was found or not. If no matching DOM node was
		   found, report an error. */
		if (matchFound == false) {
			String   errorText;
	    String   modTypeStringLower = modType.toString().toLowerCase();
			errorText = "Modification " + matchError + " - ";
			/* Get and add the modification type */
			errorText += "type (";
			errorText += modTypeStringLower;
			errorText += ")";
			/* Get and add the modification key */
			if (this.finds.size() > 0) {
				HDLmFind find;
				errorText += " key (";
				find = this.finds.get(0);
				errorText += find.getAttribute();
				errorText += ")";
				/* Get and add the modification value */
				errorText += " value (";
				find = this.finds.get(0);
				errorText += find.getValue();
				errorText += ")";
			}
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, 2, errorText);
		}
		/* Return a value showing if any changes to the HTML (DOM) were really made or
		   not */
		return matchFound;
	}
	/* Apply (if possible) an HTML style modification to a web page. This method
	   returns true if an actual change was mode. This method returns false if no
	   actual change was made. This method can only be used if the new font values
	   are selected from an array. */
	protected boolean applyStyle(ArrayList<HDLmSavedChange> savedChangesArray, Document htmlDom, HDLmModTypes type,
			                         String fontText, Integer parameterNumber, Double parameterValue) {
		if (savedChangesArray == null) {
			String  errorText = "Saved changes array passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		if (htmlDom == null) {
			String  errorText = "HTML DOM passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		if (type == null) {
			String  errorText = "Modification type value passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		if (type == HDLmModTypes.NONE) {
			HDLmAssertAction(false, "Modification type value passed to applyStyle is not set");
		}
		if (fontText == null) {
			String  errorText = "Font text passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		if (parameterNumber == null) {
			String  errorText = "Parameter number passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		if (parameterValue == null) {
			String  errorText = "Parameter value passed to applyStyle is null";
			throw new NullPointerException(errorText);
		}
		/* Assume that no match was found for this modification. This could happen for
		   any number of reasons. Of course, the required tag may not be found in the
		   DOM. We assume that no modification will actually be made. */
		boolean matchFound = false;
		/* Handle the font style update */
		ArrayList<String> localValues = this.getValues();
		Integer count = this.getValuesCount();
		int parameterIndex = (int) (count * parameterValue);
		parameterIndex = Math.min(parameterIndex, count - 1);
		String newValue = this.getValues().get(parameterIndex);
		Elements nodeList = this.find(htmlDom);
		for (Element node : nodeList) {
			matchFound = true;
			String oldStyle = "";
			if (node.hasAttr("style")) {
				oldStyle = node.attr("style");
				if (oldStyle.length() > 0 && oldStyle.charAt(oldStyle.length() - 1) != ';')
					oldStyle += ';';
			}
			String newStyleSaved = newValue;
			String newStyleAttribute = oldStyle + fontText + ":" + newValue + ";";
			node.attr("style", newStyleAttribute);
			String oldStyleSaved = getOldStyleValue(oldStyle, fontText);
			HDLmMod.saveChanges(savedChangesArray, parameterNumber, parameterValue, this.name, 
					                this.getPathMatchValue(), type, oldStyleSaved,
					                newStyleSaved);
			break;
		}
		/* Return a value showing if any changes to the HTML (DOM) were really made or
		   not
		 */
		return matchFound;
	}	
	/* Check the JSON element passed by the caller for modifications.
	   This routine recursively calls itself, to handle nesting levels.
	   The overall purpose of this routine is to note rules that have
	   fired, when they have fired. Rule firing is recorded in JSON
	   that is sent to the server. This routine finds the rule names
	   in the JSON and updates the events map. */
	protected static void  checkForModifications(final String reason,
			                                         final String error,			
			                                         final JsonElement jsonElement) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to checkForModifications is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		String  reasonStr = null;
		String  errorStr = null;
		/* Check if the caller passed a useful reason string */
		if (reason != null)
			reasonStr = reason;
		/* Check if the caller passed a useful error string */
		if (error != null)
			errorStr = error;
		/* Check for a JSON null value. We don't have anything to 
		   do for a JSON null value. */ 
		if (jsonElement.isJsonNull()) 
			;
		/* Check for a JSON primitive value. We don't have anything to 
	     do for a JSON primitive value. */ 
		else if (jsonElement.isJsonPrimitive())
			;
		/* Check for a JSON array. We must process each element of the
		   JSON array. */ 
		else if (jsonElement.isJsonArray()) {
			JsonArray   jsonArray = jsonElement.getAsJsonArray();
			int         arraySize = jsonArray.size();
			/* Loop over and process each element of the JSON array */
			for (JsonElement arrayEntry : jsonArray) {
				HDLmMod.checkForModifications(reasonStr, errorStr, arrayEntry);
			}
		}
		/* Check for a JSON object. A JSON object will contain zero or
		   more name/value pairs. If the name happens to be the name 
		   we are looking for, then we must handle the value. The value
		   in this case, will be a rule name. */ 
		else if (jsonElement.isJsonObject()) {
			/* Get the JSON object from the JSON element */
			JsonObject  jsonObject = jsonElement.getAsJsonObject();		
			/* Check if we have useful reason and error strings */
			if (jsonObject.has("reason")) {
				JsonElement   reasonElement = jsonObject.get("reason");
				if (reasonElement.isJsonPrimitive())
				  reasonStr = reasonElement.getAsJsonPrimitive().getAsString();
				else {
					reasonStr = reasonElement.toString();
				}
			}	
			/* The 'error' (without the quotes) value in the JSON object may
			   just be a simple value or it may be a complex JSON oject in 
			   it's own right. We need to handle both cases. */ 
			if (jsonObject.has("error")) {
				JsonElement   errorElement = jsonObject.get("error");
				if (errorElement.isJsonPrimitive())
				  errorStr = errorElement.getAsJsonPrimitive().getAsString();
				else {
					errorStr = errorElement.toString();
				}
			}				
			/* Check if the current JSON object has the name we are
			   looking for */ 
			if (jsonObject.has("modName")) {
				String  hostName = jsonObject.getAsJsonPrimitive("hostName").getAsString();
				String  divisionName = jsonObject.getAsJsonPrimitive("divisionName").getAsString();
				String  siteName = jsonObject.getAsJsonPrimitive("siteName").getAsString();
				String  ruleName = jsonObject.getAsJsonPrimitive("modName").getAsString();
				String  ruleType = jsonObject.getAsJsonPrimitive("modType").getAsString();
				/* Try to get the parameter number from the JSON object */
				JsonElement   jsonParameterNumber = jsonObject.get("parmNumber");
				Integer   parameterNumber = null;
				if (jsonParameterNumber.isJsonNull())
					parameterNumber = null;
				else
					parameterNumber = jsonParameterNumber.getAsInt();
				/* We need to check for a very special case here. The rule name and rule
				   type may show that this was an internally generated rule. The event is
				   skipped in this case. */
				boolean   internalEvent = false;
				String    modificationName = HDLmDefines.getString("HDLMLOADPAGEMODNAME"); 
		    if (modificationName == null) {
			    String  errorFormat = "Define value for VISIT (a rule type) page name not found (%s)";
			    String  errorText = String.format(errorFormat, "HDLMLOADPAGEMODNAME");
		 	    HDLmAssertAction(false, errorText);		    	
		    }
				String    modificationNameOld = HDLmDefines.getString("HDLMLOADPAGEMODNAMEOLD"); 
		    if (modificationNameOld == null) {
			    String  errorFormat = "Define value for VISIT (a rule type) old page name not found (%s)";
			    String  errorText = String.format(errorFormat, "HDLMLOADPAGEMODNAMEOLD");
		 	    HDLmAssertAction(false, errorText);		    	
		    }
				String    modificationType = HDLmModTypes.VISIT.toString().toLowerCase();
				if ((ruleName.equals(modificationName) ||
						 ruleName.equals(modificationNameOld)) &&
						ruleType.equals(modificationType)) 
				  internalEvent = true;
				/* We need to check for a very special case here. The rule name and rule
			     type may show that this was an internally generated rule. The event is
			     skipped in this case. */
				if (!internalEvent) {
				  /* Note that an event has occurred. We only consider the event to
			  	   have occurred if the reason string is something other than 'failure'
			  	   (without the quotes). This code an be invoked even the reason is
				     'failure' (without the quotes). In that case, the event did not
				     really occur. */
				  if (!reasonStr.equals("failure")) {
					  HDLmEvent.eventOccurred(hostName, divisionName, 
						  	                    siteName, ruleName,
							                      parameterNumber);
				  }
				}
				/* Note that a rule was used. This code is always used even
				   if the reason is 'failure' (without the quotes).  */
				if (!internalEvent) {
				  HDLmRule.ruleUsed(hostName, divisionName, 
						                siteName, ruleName, errorStr);
				}
			}			
			/* The current JSON object does not contain the name we are looking
			   for. Each name/value pair in the object must be processed. */  
			else {
		  	/* Get all of the name/value pairs as a set of entries */
		  	Set<Map.Entry<String,JsonElement>> 	jsonSet = jsonObject.entrySet();
		  	for (Map.Entry<String, JsonElement>  jsonEntry : jsonSet) {
				  /* Get the name and value for the current entry. The name 
				     may be the name we are looking for. */ 
				  String        entryName = jsonEntry.getKey();
				  JsonElement   entryJsonElement = jsonEntry.getValue(); 			
				  HDLmMod.checkForModifications(reasonStr, errorStr, entryJsonElement);
			  }
		  }		
		}
	}
	/* Get a copy of the current modification. The copy is returned to the caller  
	   as a standard object. Note that this process should remove all of the
	   permissions from the returned object. In other words, the all of the
	   properties of the returned object should be public. These comments do 
	   not apply to the Java version of this routine. The Java version just 
	   makes a deep copy of the current modification object. */
	protected HDLmMod      copyMod() {
		HDLmMod newObj = new HDLmMod(this);
		newObj.setIfNotSetTimes();
		return newObj;
	}
	/* This method copies all of the modification fields from an old
	   modification to the current one. This routine is used by copy
	   constructors as need be. */ 
	protected void         copyModFields(final HDLmMod oldMod) {
		/* Check if the old modification passed to this routine is null or not */
		if (oldMod == null) {
			String  errorText = "Old details reference used to build modification details is null";
			throw new NullPointerException(errorText);
		}
		this.comments = oldMod.getComments();
		this.created = oldMod.getCreated();
		this.cssSelector = oldMod.getCssSelector();
		this.enabled = oldMod.getEnabled();
		this.errorCount = oldMod.getErrorCount();
		this.extra = oldMod.getExtra();
		/* We need to deep copy the finds. This requires several steps. Of course, we
		   can only copy the old finds, if we had any. If the old finds field was null,
		   we don't have any old finds to copy. */
		ArrayList<HDLmFind> oldFinds = oldMod.getFinds();
		if (oldFinds != null) {
			ArrayList<HDLmFind> newFinds = new ArrayList<HDLmFind>();
			for (HDLmFind oldFind : oldFinds) {
				HDLmFind newFind = new HDLmFind(oldFind);
				newFinds.add(newFind);
			}
			this.finds = newFinds;
		}
		this.lastModified = oldMod.getLastModified();
		this.name = oldMod.getName();
		this.nodeIden = oldMod.getNodeIden();
		if (this.nodeIden != null)
			this.nodeIden = new HDLmNodeIden(this.nodeIden);
		this.parameter = oldMod.getParameterNumber();
		this.type = oldMod.getType();
		this.pathValue = oldMod.getPathValue();
		this.pathre = oldMod.getPathValueRe();
		this.pathMatch = oldMod.getPathValueMatch();
		this.useMode = oldMod.getUseMode();
		this.value = oldMod.getValue();
		this.valueSuffix = oldMod.getValueSuffix();
		/* We need to copy the values. Of course, we can only copy the old values, if we
		   had any. If we don't have any old values, then we can not copy them. */
		ArrayList<String> oldValues = oldMod.getValues();
		if (oldValues != null) {
			this.values = new ArrayList<String>(oldValues);
		}
		this.valuesCount = oldMod.getValuesCount();
		this.xPath = oldMod.getXPath();
	}	
	/* Process the Java object passed by the caller. The Java object
	   should be for just one site. Extract all of the modifications
	   for the site and put them in an array. */
	protected static ArrayList<HDLmMod> extractMods(String pathValueStr, 
			                                            HDLmTree siteObj,
			                                            HDLmPassThruStatus passThru,
			                                            HDLmUsePathValue usePathValue) {
		/* Check a few values passed by the caller */
		if (pathValueStr == null) {
			String  errorText = "Path value string passed to extract modifications is null";
			throw new NullPointerException(errorText);
		}
		/* Build an empty array list of modifications */
		ArrayList<HDLmMod> outMods = new ArrayList<HDLmMod>();
		/* Check if the caller passed a null value. This can happen if we don"t have any
		   modifications for the current site. */
		if (siteObj == null)
			return outMods;
		HDLmTreeTypes type = siteObj.getType();
		if (type != HDLmTreeTypes.SITE)
			return outMods;
		/* Get the children (the actual modifications) of the site node */
		ArrayList<HDLmTree> children = siteObj.getChildren();
		for (HDLmTree child : children) {
			/* Declare and define the match variable for use below */
			boolean   match = true;
			/* Each of the child modification objects will have a match object.
			   The check() method of the match object can be used to check for
			   a path value match or not. */ 
			if (usePathValue == HDLmUsePathValue.USEPATHVALUEOK) {
			  match = child.getDetails().getPathValueMatch().match(pathValueStr);
			}
		  /* Get the child path value string for use later */
			String  childPathValue = child.getDetails().getPathMatchValue();
			if (match == false)
				continue;
			HDLmMod childDetails = child.getDetails();
			/* If we are in pass-through mode, then mark the current modification as
		     disabled. All modifications are disabled in pass-through mode. This
		     doesn't really work. Some critical modifications are disabled even
		     though they are really needed. The current approach is to just 
		     manually disable almost all of the modifications while leaving the
		     critical modifications enabled. */
			if (passThru == HDLmPassThruStatus.PASSTHRUOK) {
				/* We must make a copy of the modification here because we are 
				   going to change the modification. We don't want to change the
				   original modifications. */
				HDLmMod newMod = new HDLmMod(childDetails);
				newMod.setIfNotSetTimes();
				newMod.setEnabled((Boolean) false);
				outMods.add(newMod);
			}
			else {
				outMods.add(childDetails);				
			}
		}
		return outMods;
	}
	/* This is the general purpose find function (method). This function tries to
	   use CSS Selector search informtion to find the HTML elements that must be
	   modified. If no CSS Selector information is available, then this function
	   tries to use the XPath search value (if one exists). If no CSS Selector and
	   XPath search information is available, then the modification find array is 
	   used. */
	protected Elements find(Document HTMLDom) {
		if (HTMLDom == null) {
			String  errorText = "HTML DOM passed to find is null";
			throw new NullPointerException(errorText);
		}
		Elements nodeList = null;
		/* Check if a CSS Selector string is specified or not. If we actually have a CSS
		   Selector string, then we use it in preference to the XPath search value and
		   the find array. Of course, we may not always have a CSS Selector string to
		   use. */
		if (this.cssSelector != null && !StringUtils.isWhitespace(this.cssSelector)) {
			nodeList = HDLmHtml.querySelectorAll(HTMLDom, this.cssSelector);
		}
		/* Check if an XPath search string is specified or not. If we actually have an
		   XPath search string, then we use it in preference to the find array. Of
		   course, we may not always have an XPath search string to use. */
		else if (this.xPath != null && !StringUtils.isWhitespace(this.xPath)) {
			String  errorText = "XPath can not be used with HTML documents";
			Exception exception = new NotImplementedException(errorText);
			throw new NotImplementedException(errorText, exception);
		}
		/* Since we didn't have any CSS Selector or XPath search information, we must
		   try to locate the HTML elements we need using the find array. */
		else
			nodeList = HDLmFind.processFindsArray(HTMLDom, this.finds);
		return nodeList;
	}
	/* This method gets the name of the array field for the current 
	   modification type. Not all types have an array field. However,
	   some do. We need to know the correct name of the array field
	   to build the correct JSON. This routine will return a null
	   value if the current type does not have an array field (this
	   is not an error). This routine will return a string if the
	   current type does have an array field. */ 
	protected static String getArrayName(HDLmModTypes type) {
		/* Check if the type passed by the caller is a null value */
		if (type == null) {
			String  errorText = "Modification type value passed to getArrayName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the type passed by the caller is invalid */
		if (type == HDLmModTypes.NONE) {
			String  errorText = "Modification type value passed to getArrayName is invalid";
			throw new IllegalArgumentException(errorText);
		}
		String  typeString = type.toString();
		typeString = typeString.toLowerCase();
		/* Try to get some information for the current type */
		JsonElement   jsonElementInfoData = HDLmModInfoData;
		if (jsonElementInfoData == null)
			HDLmAssertAction(false, "JSON data for modifications is null");
		JsonElement   jsonElementType = HDLmJson.getJsonValue(jsonElementInfoData, typeString);
		if (jsonElementType == null) {
			String  errorFormat = "No JSON information for type (%s)";
			String  errorText = String.format(errorFormat, typeString);
			HDLmAssertAction(false, errorText);
		}	
		/* We can now get the fields for the current type */
		JsonElement   jsonElementFields = HDLmJson.getJsonValue(jsonElementType, "fields");
		if (jsonElementFields == null) {
			String  errorFormat = "No JSON field information for type (%s)";
			String  errorText = String.format(errorFormat, typeString);
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON element to a JSON array if possible */
		if (!jsonElementFields.isJsonArray()) 
			HDLmAssertAction(false, "Fields JSON element is not a JSON array");
		JsonArray   jsonArray = jsonElementFields.getAsJsonArray();
		if (jsonArray == null)
			HDLmAssertAction(false, "JSON field array for modifications is null");
		int   jsonArraySize = jsonArray.size();
		/* Get the information for the last field */
		JsonElement   jsonElementField = jsonArray.get(jsonArraySize-1);
		if (jsonElementField == null)
			HDLmAssertAction(false, "Last JSON field for modifications is null");
		/* Check if we have a data type field. If we do not have a data
		   type field, then we don't have an array name. */
		String  dataTypeString = HDLmJson.getJsonString(jsonElementField, "datatype");
		if (dataTypeString == null)
			return null;
		if (!dataTypeString.equals("array"))
			return null;
	  String  arrayNameString = HDLmJson.getJsonString(jsonElementField, "source");
		return arrayNameString;
	}
	/* Get the comments from a modification and return the comments string to the caller */
	protected String       getComments() {
		return this.comments;
	}
	/* Get the created time from a modification and return the created Instant to the caller */
	protected Instant      getCreated() {
		return this.created;
	}
	/* Get the CSS Selector from a modification and return it to the caller */
	protected String       getCssSelector() {
		return this.cssSelector;
	}
	/* Get the modification enablement status and return it to the caller */
	protected boolean      getEnabled() {
		return this.enabled;
	}
	/* Get the modification error count and return it to the caller */
	protected int          getErrorCount() {
		return this.errorCount;
	}
	/* Get the modification extra information value and return it to the caller */
	protected String       getExtra() {
		return this.extra;
	}
	/* Get the finds associated with a modification and return them to the caller */
	protected ArrayList<HDLmFind>  getFinds() {
		return this.finds;
	}
	/* Process the modifications passed by the caller. Find the highest
	   parameter number in the modifications. This routine returns a null
	   value, if no parameter numbers can be found. */
	protected static Integer getHighParmNumber(ArrayList<HDLmMod> mods) {
		/* Check if the modifications array passed by the caller is null */
		if (mods == null) {
		  String  errorText = "Modifications array list passed to getHighParmNumber is null";
      throw new NullPointerException(errorText);
		}
		int   minValue = Integer.MIN_VALUE;
		int   modsLength = mods.size();
		Integer   maxParmNumber = null;
		for (int i = 0; i < modsLength; i++) {
			/* Get the current parameter number, if any */
			HDLmMod   currentMod = mods.get(i);
			Integer   modParmNumber = currentMod.parameter;
			/* Skip the current modification if the parameter number is 
			   not set */
			if (modParmNumber == null)
        continue;			
			/* Create the return value, if need be */
			if (maxParmNumber == null)
				maxParmNumber = Integer.valueOf(0);
	    /* Check if the current parameter number is greater than that highest value
         we have seen so far */
      if (modParmNumber > minValue) {
        minValue = modParmNumber;
        maxParmNumber = modParmNumber;
      }
		}		
		return maxParmNumber;
	}	
	/* Get the JSON string for a modification. This function does all of the work
	   needed to get the JSON version of a modification. Note that many of the
	   modification properties are private or protected. This code bypasses all
	   permission restrictions. */
	protected String getJsonSerializeNulls() {
		Gson gson = HDLmMain.gsonSerializeNullsMain;
		String jsonStr = gson.toJson(this);
		return jsonStr;
	}
	/* Get the JSON string for a modification. This function does all of the work
		 needed to get the JSON version of a modification. Note that many of the
		 modification properties are private or protected. This code bypasses all
		 permission restrictions. */
	protected String getJsonSpecialSerializeNulls() {
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After entry in getJsonSpecial");
		Gson  gson = HDLmMain.gsonSerializeNullsMain;
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After gson create in getJsonSpecial");
		/* We need to make a copy of the current modification before we make
		   any changes to it. We don't want to change the original modification. */
		HDLmMod  newMod = new HDLmMod(this);
		/* We need to check if errors were found building the current
		   modification. If any errors were found, then we need to mark
		   the current modification as disabled. This change is only 
		   made to the copy of the current modification. The current
		   modification is not altered. */ 
		if (newMod.errorCount > 0)
			newMod.enabled = false;
		/* We need to check and fix some of the fields in the modification. Null
		   values will cause problems with JavaScript. */
		if (newMod.getFinds() == null)
			newMod.setFinds(new ArrayList<HDLmFind>());
		/* An array of finds did exist. We need to check each find for null 
		   values and change them to empty strings. */
		else {
			ArrayList<HDLmFind>  oldFinds = newMod.getFinds();
			ArrayList<HDLmFind>  newFinds = new ArrayList<HDLmFind>();
			for (HDLmFind oldFind : oldFinds) {
				HDLmFind   newFind = new HDLmFind(oldFind);
				if (newFind.getTag() == null)
					newFind.setTag("");
				if (newFind.getAttribute() == null)
					newFind.setAttribute("");
				if (newFind.getValue() == null)
					newFind.setValue("");
				newFinds.add(newFind);			
			}			
			newMod.setFinds(newFinds);
		}
		if (newMod.getValues() == null)
			newMod.setValues(new ArrayList<String>());
		if (newMod.getCssSelector() == null)
			newMod.setCssSelector("");
		if (newMod.getExtra() == null)
			newMod.setExtra("");
		if (newMod.getLastModified() != null)
			newMod.setDummyLastModified(newMod.getLastModified().toString());
		if (newMod.getName() == null)
			newMod.setName("");
		/* Try to set the new modification path value value from old modification
		   match object, if possible */
		HDLmMatch  oldMatch = this.getPathValueMatch();
		if (oldMatch != null) {
			String  oldMatchValue = oldMatch.getValue();
			if (oldMatchValue != null)
				newMod.setPathValue(oldMatchValue);			
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before path match check in getJsonSpecial");
		/* Try to set the new modification path value regex boolean from old modification
	     match object, if possible */
		if (oldMatch != null) {
			HDLmMatchTypes  oldMatchType = oldMatch.getType();
			Boolean   regexInUse = (oldMatchType != HDLmMatchTypes.NONE);
			newMod.setPathValueRe(regexInUse);			
		}		 
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before set zero-length string in getJsonSpecial");
		if (newMod.getPathValue() == null)
			newMod.setPathValue("");
		if (newMod.getValue() == null)
			newMod.setValue("");
		if (newMod.getValueSuffix() == null)
			newMod.setValueSuffix("");
		if (newMod.getXPath() == null)
			newMod.setXPath("");
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After set zero-length string in getJsonSpecial");
		/* Convert the new (and possibly modified) modification to a set
		   of JSON elements. The JSON elements are converted to a string
		   later. */
		JsonElement   jsonElement = gson.toJsonTree(newMod);
		/* Remove a set of keys from the current JSON object */
		HDLmJson.removeJsonKey(jsonElement, "comments");
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After comments removal in getJsonSpecial");
		HDLmJson.removeJsonKey(jsonElement, "errorCount");
		HDLmJson.removeJsonKey(jsonElement, "created");
		HDLmJson.removeJsonKey(jsonElement, "lastModified");
		HDLmJson.removeJsonKey(jsonElement, "dummyCreated");
		/* Convert the new (and possibly modified) modification to a JSON
	     string */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After error count removal in getJsonSpecial");
		String   jsonStr = HDLmJson.getStringJsonNulls(jsonElement);
		/* The JSON string needs to be modified in a few ways. Some 
		   values really don't need to be serialized. */ 
		jsonStr = jsonStr.replace("\"dummyLastModified\"", "\"lastmodified\"");
		jsonStr = jsonStr.replace("\"dummyTable\":null,", "");  
		jsonStr = jsonStr.replace("\"dummyType\":null,", "");
		jsonStr = jsonStr.replace("\"passThru\":null,", "");
		jsonStr = jsonStr.replace("\"updated\":null,", "");
		/* We need to make a change in the JSON string at this point. The
		   modification type will be in uppercase. However, we need it in
		   lower case. */
		HDLmModTypes   newType  = newMod.getType();
		String         newTypeString = newType.toString();
		String         newTypeLower = newTypeString.toLowerCase();
		jsonStr = jsonStr.replace("\"type\":\"" + newTypeString + "\"",  
				                      "\"type\":\"" + newTypeLower + "\"");
		jsonStr = jsonStr.replace("cssSelector", "cssselector");
		jsonStr = jsonStr.replace("nodeIden", "nodeiden");
		jsonStr = jsonStr.replace("pathValue", "path");
		jsonStr = jsonStr.replace("xPath", "xpath");
		/* The JavaScript code seems to use 'parameter' (without the quotes), not
		   'parameterNumber' (without the quotes) in the rest of the code. We should
		   stick with 'parameter' (without the quotes) here as well. */ 
		/* jsonStr = jsonStr.replace("parameter", "parameterNumber"); */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After \"parameter\" change in getJsonSpecial");
		jsonStr = jsonStr.replace("nodeEnabled", "enabled");
		jsonStr = jsonStr.replace("nodeType", "type");
		jsonStr = jsonStr.replace("nodeCounts", "counts");
		jsonStr = jsonStr.replace("nodeAttributes", "attributes");
		jsonStr = jsonStr.replace("nodeGrand", "grandparent");
		jsonStr = jsonStr.replace("\"grandparent\":null,",  "");
		jsonStr = jsonStr.replace("nodeParent", "parent");
		/* We may need to make some changes to the JSON at this point. 
		   We may need to add the count of values. This value will not
		   be added to the JSON because it is marked as transient. */
		String  valuesCountStr = "\"valuesCount\":" + String.valueOf(valuesCount) + ",\"values\"";
		jsonStr = jsonStr.replace("\"values\"", valuesCountStr);
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After values removal in getJsonSpecial");
		return jsonStr;
	}
	/* This method builds a JSON element with the complete structure 
	   needed to build the rule tree. The format of this JSON element
	   does not exactly match the structure that comes back from
	   the bridge. 
	   
	   The bridge returns a row for each node in the node tree. As    
	   a practical matter all of the rows are obtained with a single 
	   REST call to the bridge server. 
	   
	   The caller can optionally pass a host name value. The host name 
	   value will be used to filter the rule tree. Note that the host 
	   name may not match any actual host names. This is not an error
	   condition. 
	   
	   This routine does not appear to be actually in use. The web socket
	   code that invokes this code appears to be inactive.
	   
	   Pass-through is part of the method name to help distinguish between
	   different routines that used to have the same name. */
	protected static JsonElement getJsonTreePassThru(final String contentType,
			                                             final String hostName) {
		/* Check if the content type reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to getJsonTreePassThru is null";
			throw new NullPointerException(errorText);
		}	
		/* Get a fresh copy of all of the modifications. The new rule
	     is added to a fresh copy of the modifications. */ 
    HDLmTree   topTreeNode = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO); 
	  if (topTreeNode == null) {
		  HDLmAssertAction(false, "Null modifications tree returned by buildNodeTreeMain");
	  } 
		/* Store the reference to the new top tree. The new top tree was
	     built using the latest set of rules. We might as well use the
	     latest set of rules. */
	  HDLmTree.setNodePassTreeTop(topTreeNode);
	  /* We can now try to build a set of JSON elements that represent
       the entire node tree and all of the modifications. This is a 
       rather large tree, in many cases. */
    JsonElement   jsonElementTree = HDLmTree.getJsonTreeTree(contentType,
                                                             topTreeNode);
		/* Create several initial JSON objects for use later */
		JsonArray   jsonDataArray = new JsonArray();
		JsonArray   jsonModsArray = new JsonArray();
		JsonObject  jsonDataObject = new JsonObject();
		JsonObject  jsonNestedObject = new JsonObject();
		/* Check if a non-null (actual) host name was passed to this routine.
		   If a host name was provided, we want to get rid of all of the JSON
		   elements (if any) for all of the other host names (if any). */
		if (hostName != null) {
			/* Get the children of the top tree JSON element */
		  JsonElement   jsonChildrenElement = HDLmJson.getJsonValue(jsonElementTree, "children");
		  if (jsonChildrenElement == null) {
		  	String   errorText = "JSON children not obtained from JSON tree top element";
		  	HDLmAssertAction(false, errorText);			
		  }
		  /* Convert the children to a JSON array */
		  JsonArray   jsonChildrenArrayElement = jsonChildrenElement.getAsJsonArray();
      int   arrayIndex = -1;
      ArrayList<Integer>  removeList = new ArrayList<Integer>();
		  /* Process all of the children of the parent node. A moderately complex
		     process is used here. Instead of actually removing the JSON elements
		     that we don't want, we accumulate the index values of the JSON 
		     elements we don't want. This eliminates the need to modify the 
		     JSON array as we are scanning it. 
		     
		     The code below tries to get a company name from JSON. This may
		     not work because the company name may only be in the node path. */ 
		  for (JsonElement jsonChildElement : jsonChildrenArrayElement) {		
		  	arrayIndex += 1;
		  	/* Get the company name for the current child. This probably won't 
		  	   work. The 'name' (without the quotes) is only stored in the 
		  	   details. The call below will probably fail. The node path should
		  	   contains the company name as the last entry. */
		  	String      companyName = HDLmJson.getJsonString(jsonChildElement, "name");
		  	if (!companyName.equals(hostName)) 
		  		removeList.add(arrayIndex);
		  }
		  /* Note that the JSON array is processed (deliberately) in reverse 
		     order. This avoids the problem of index values changing as the
		     array is modified. */
		  int   removeListSize = removeList.size();
		  for (arrayIndex = removeListSize-1; arrayIndex >= 0; arrayIndex--) {
		  	int   removeIndex = removeList.get(arrayIndex);
		  	jsonChildrenArrayElement.remove(removeIndex);
		  }
		  /* Convert the JSON array (possibly modified) back to a JSON element */
		  jsonChildrenElement = jsonChildrenArrayElement;
		  HDLmJson.setJsonValue(jsonElementTree, "children", jsonChildrenElement);	
		}		
		/* Add the tree to the JSON mods array */
		jsonModsArray.add(jsonElementTree);
		/* Build the JSON nested object */
		jsonNestedObject.addProperty("mods", HDLmJson.getStringJson(jsonModsArray)); 
		/* Build the JSON data array */
		jsonDataArray.add(jsonNestedObject);
		/* Finish the JSON data object */
		jsonDataObject.add("data",  jsonDataArray);
		/* Add a few more values to the final JSON element */		
		HDLmJson.setJsonInteger(jsonDataObject, "rows_returned", 1);
		HDLmJson.setJsonString(jsonDataObject, "comments", "io for test_2");		
		return jsonDataObject;
	}
	/* Get the last modified time from a modification and return the last modified Instant
	   to the caller */
	protected Instant      getLastModified() {
		return this.lastModified;
	}
  /* This method returns a Boolean showing if a modification type
	   uses (requires) a parameter number or not. Most types of rules
	   require parameter numbers. However, some do not. This method
	   takes a modification type string and returns a boolean showing
	   if a parameter number is needed or not. If the type is unknown,
	   than a NULL value is returned to the caller. */
	protected static Boolean  getModificationTypeParmNumberUsed(String typeStr) {
	  /* Check if information for type specified by the caller exists */
	  if (HDLmJson.hasJsonKey(HDLmModTypeInfo, typeStr)) {
	  	JsonElement   jsonElementType = HDLmJson.getJsonValue(HDLmModTypeInfo, typeStr);
	  	if (!jsonElementType.isJsonNull()) {
	  		JsonElement   jsonElementParm = HDLmJson.getJsonValue(jsonElementType, "parmnumberused");
	  		if (!jsonElementParm.isJsonNull()) {
	  		  boolean   booleanValue = jsonElementParm.getAsBoolean();
	  		  return booleanValue;
	    	}
	  	}
	  }
	  return null;
	}
  /* This method returns a string that contains the values name 
     or a null value. The values name (if any) is returned to the
	   caller. Some types of modifications have a values name. Other
	   types of modifications do not. This method takes a modification 
	   type string and returns a string that contains the values name 
	   or a null values. If the type is unknown, than a null value is 
	   returned to the caller. */
	protected static String  getModificationTypeValuesName(String typeStr) {
	  /* Check if information for type specified by the caller exists */
	  if (HDLmJson.hasJsonKey(HDLmModTypeInfo, typeStr)) {
	 	  JsonElement   jsonElementType = HDLmJson.getJsonValue(HDLmModTypeInfo, typeStr);
	 	  if (!jsonElementType.isJsonNull()) {
	 		  JsonElement   jsonElementParm = HDLmJson.getJsonValue(jsonElementType, "valuesname");
	 		  if (!jsonElementParm.isJsonNull()) {
	 		    String  stringValue = jsonElementParm.getAsString();
	 		    return stringValue;
	   	  }
	 	  }
	  }
	  return null;
	}
	/* Get the modifications from a set of JSON. The overall purpose of 
	   this routine is to note rules that have fired, when they have fired.
	   Rule firing is recorded in JSON that is sent to the server. This
	   routine finds the rule names in the JSON and updates the events map. */
	protected static void  getModificationsFromJson(final String jsonString) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonString == null) {
			String  errorText = "String containing JSON passed to getModificationsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Create a new JSON parser for use below */
    JsonParser   parser = new JsonParser();  
    JsonElement  jsonElement = parser.parse(jsonString); 
    HDLmMod.checkForModifications(null, null, jsonElement);	
	}
	/* Get the modification information JSON element and return it
     to the caller */
	protected static JsonElement getModInfo() {
		return HDLmModTypeInfo;
	}
	/* Get the modification name value return it to the caller */
	protected String getName() {
		return this.name;
	}
	/* Get the modification node identifier reference and return 
	   it to the caller */
	protected HDLmNodeIden getNodeIden() {
		return this.nodeIden;
	}
	/* Get the old style value from a style string, if possible. The old style value
	   may not be found. If the old style value is not found, a null value will be
	   returned to the caller. The caller must pass the overall old style string,
	   and the name of the specific style we are looking for. The style name should
	   not end with a ':'. This code will provide the ':' character as need be. */
	protected static String getOldStyleValue(String oldStyle, String styleName) {
		if (oldStyle == null) {
			String  errorText = "Old style string passed to getOldStyleValue is null";
			throw new NullPointerException(errorText);
		}
		if (styleName == null) {
			String  errorText = "Style name passed to getOldStyleValue is null";
			throw new NullPointerException(errorText);
		}
		String oldValue = null;
		/* Add a trailing colon character to the style name we are looking for */
		styleName += ':';
		/* Search for the style name we are looking for */
		int styleIndex = oldStyle.indexOf(styleName);
		if (styleIndex < 0)
			return oldValue;
		/* Search for the next semicolon character */
		int startIndex = styleIndex + styleName.length();
		int endIndex = oldStyle.indexOf(';', startIndex);
		if (endIndex < 0)
			endIndex = oldStyle.length();
		return oldStyle.substring(startIndex, endIndex);
	}
	/* Get the modification parameter number and return it to the caller */
	protected Integer getParameterNumber() {
		return this.parameter;
	}
  /* Get the value of the tree type field and return it to the caller.
     This value does not really exists for a modification. We return
     a null value as a consequence. */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return null;
  }
	/* Get the path value string from a modification and return it to the caller */
	protected String       getPathValue() {
		return this.pathValue;
	}
	/* Get the path value match reference from a modification and return it to the caller */
	protected HDLmMatch    getPathValueMatch() {
		return this.pathMatch;
	}
	/* Get the modification path value regex status and return it to the caller */
	protected boolean      getPathValueRe() {
		return this.pathre;
	}
	/* Get the path value match reference type from a modification and return 
     it to the caller */
  protected HDLmMatchTypes  getPathValueType() {
	  return this.pathMatch.getType();
  }
	/* Get the path value match value reference from a modification and return 
     it to the caller */
  protected String       getPathMatchValue() {  
  	return this.pathMatch.getValue();
  }
	/* Get the tree information JSON element and return it
     to the caller */
	protected static JsonElement getTreeInfo() {
		return HDLmModTreeInfo;
	}
	/* Get the use mode value (a string) and return it to the caller */
	protected String       getUseMode() {
		return this.useMode;
	}
	/* Get the type from a modification and return it to the caller */
	protected HDLmModTypes  getType() {
		return this.type;
	}
	/* Get the modification value and return it to the caller */
	protected String       getValue() {
		return this.value;
	}
	/* Get the modification value suffix and return it to the caller */
	protected String       getValueSuffix() {
		return this.valueSuffix;
	}
	/* Get the modification values and return them to the caller */
	protected ArrayList<String> getValues() {
		return this.values;
	}
	/* Get the modification values count and return it to the caller */
	protected int          getValuesCount() {
		return this.valuesCount;
	}
	/* Get the modification XPath value and return it to the caller */
	protected String       getXPath() {
		return this.xPath;
	}
	/* Check if the array size value shows that the array is empty or not (empty 
	   is an error, in most cases). If the array size value shows that the array 
	   is empty, report the empty array error unless a special value is passed 
	   by the caller. Note that if the array size value is a null value, then the 
	   array size null value is taken as evidence (proof) of some kind of error. 
	   
		 This routine returns the array count to the caller. The caller provides all
		 of the values used to report the error. This routine builds the error message
		 text and reports the error. Note that the caller provides the array name.
		  
		 If the array address is a null value, then the array size reference will also
		 be set to a null value. That is how the caller shows that the array address
		 is a null value. If the array size reference is not null, it will have the
		 correct array size (which may be zero). */	
	protected static Integer modArrayCheck(HDLmEditorTypes editorType, 
			                                   MutableInt errors, 
			                                   JsonObject jsonObject, 
			                                   String name, 
			                                   Integer arraySize, 
			                                   HDLmZeroLengthOk zeroLengthOk) {
		return modArrayCheck(editorType, 
				                 errors, 
				                 jsonObject, 
				                 name, 
				                 arraySize, 
				                 zeroLengthOk,
				                 HDLmReportErrors.REPORTERRORS);
	}
	protected static Integer modArrayCheck(HDLmEditorTypes editorType, 
			                                   MutableInt errors, 
			                                   JsonObject jsonObject, 
			                                   String name, 
			                                   Integer arraySize,
			                                   HDLmZeroLengthOk zeroLengthOk,
			                                   HDLmReportErrors reportErrors) {
		if (editorType == null) {
			String  errorText = "Editor type value passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		if (arraySize == null) {
			String  errorText = "Array size reference passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		if (zeroLengthOk == null) {
			String  errorText = "Zero-length OK value passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
			String  errorText = "Report errors value passed to modArrayCheck is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the array size value has been set or not. If the array size 
		   value has been set, check if the value is valid. Zero-length array sizes
		   are generally not valid. However, in at least one case, a zero-length
		   array size is OK. */
		if (arraySize != null) {
			if (arraySize > 0 && zeroLengthOk == HDLmZeroLengthOk.ZEROLENGTHNOTOK)
				return arraySize;
			if (arraySize >= 0 && zeroLengthOk == HDLmZeroLengthOk.ZEROLENGTHOK)
				return arraySize;			
		}
		/* Report the error */
		int errorNumber = 8;
		String  errorText = "Modification JSON not found or empty array";
		HDLmMod.reportErrorNoValue(editorType, errors, 
				                       jsonObject, name, 
				                       errorText, 
				                       errorNumber, 
				                       reportErrors);
		return null;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static Boolean modFieldBoolean(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     Set<String> jsonKeys,
			                                     String name) {
		return modFieldBoolean(editorType, 
				                   errors, 
				                   jsonObject, 
				                   jsonKeys, 
				                   name, 
				                   HDLmReportErrors.REPORTERRORS);
	}
	protected static Boolean modFieldBoolean(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     Set<String> jsonKeys, 
			                                     String name,
			                                     HDLmReportErrors reporErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		Boolean newBoolean = null;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				newBoolean = jsonElement.getAsBoolean();
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, 
					                       errors, 
					                       jsonObject, 
					                       name, 
					                       "Modification JSON missing field", 
					                       3, 
					                       reporErrors);
		return newBoolean;
	}
	/* Try to access a date-time value (really an instant) */
	protected static Instant  modFieldDateTime(HDLmEditorTypes editorType, 
																			       MutableInt errors, 
																			       JsonObject jsonObject, 
																			       Set<String> jsonKeys, 
																			       String name,
																			       HDLmReportErrors reporErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldBoolean is null";
			throw new NullPointerException(errorText);
		}
		boolean   fieldFound = false;
		Instant   newInstant = null;
		String    newString = null;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			JsonElement jsonElement = jsonObject.get(name);
  		/* LOG.info(name); */
  		/* LOG.info(jsonElement.toString()); */
  		/* LOG.info(jsonObject.toString()); */  
			if (!jsonElement.isJsonNull()) {
			  newString = jsonElement.getAsString();
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false) {
			/* if (fieldFound == false) { */
				/* LOG.info("Not found"); */
				/* LOG.info(name); */
			  /* return Instant.now(); */ 
			/* } */
	  	HDLmMod.reportErrorNoValue(editorType, 
																 errors, 
																 jsonObject, 
																 name, 
																 "Modification JSON missing field", 
																 3, 
																 reporErrors);
		}
		/* Try to convert the date-time string to an instant */
		else {
			try {
				/* For some reason the strings passed to this routine don't always
				   end with a 'Z' character. Add the 'Z' character, if need be. */ 
				int  newStringLength;
				if (newString != null) 
					newStringLength = newString.length();
				else
					newStringLength = 0;
				if (newStringLength > 0 && newString.charAt(newStringLength-1) != 'Z')
					newString += "Z";
				newInstant = Instant.parse(newString);
			} 
			catch (DateTimeParseException e) {
				HDLmMod.reportErrorValue(editorType, 
						                     errors, 
						                     jsonObject, 
						                     name, 
						                     newString,
                                 "Modification JSON invalid date-time value", 
                                 4, 
                                 reporErrors);			
			}
		}
		return newInstant;
  }
	/* Try to access all of the fields used for a find. Each find defines one level
	   of a (possibly) multilevel search used to find nodes in the DOM. All of the
	   fields checked below must be present in each find JSON object. */
	protected static HDLmFind modFieldFind(HDLmEditorTypes editorType, 
			                                   MutableInt errors, 
			                                   JsonObject jsonObject, 
			                                   Set<String> jsonKeys) {
		return modFieldFind(editorType, errors, 
				                jsonObject, jsonKeys, 
				                HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmFind modFieldFind(HDLmEditorTypes editorType, 
			                                   MutableInt errors, 
			                                   JsonObject jsonObject, 
			                                   Set<String> jsonKeys,
			                                   HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldFind is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldFind is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldFind is null";
			throw new NullPointerException(errorText);
		}
		/* Check each of the fields in the find JSON object */
		String   newTag = null;
		String   newTagInfo = HDLmMod.modFieldString(editorType, errors, 
				                                         jsonObject, jsonKeys, 
				                                         "tag", HDLmWhiteSpace.WHITESPACEOK, 
				                                         reportErrors,
				                                         HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newTagInfo != null && !StringUtils.isWhitespace(newTagInfo)) {
			newTag = newTagInfo;
		}
		String   newAttribute = null;
		String   newAttributeInfo = HDLmMod.modFieldString(editorType, errors,
				                                               jsonObject, jsonKeys, "attribute", 
				                                               HDLmWhiteSpace.WHITESPACEOK, 
				                                               reportErrors,
				                                               HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newAttributeInfo != null && !StringUtils.isWhitespace(newAttributeInfo)) {
			newAttribute = newAttributeInfo;
		}
		String   newValue = null;
		String   newValueInfo = HDLmMod.modFieldString(editorType, errors, 
				                                           jsonObject, jsonKeys, 
				                                           "value", 
				                                           HDLmWhiteSpace.WHITESPACEOK, 
				                                           reportErrors,
				                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newValueInfo != null && !StringUtils.isWhitespace(newValueInfo)) {
			newValue = newValueInfo;
		}
		HDLmFind newFind = new HDLmFind(newTag, newAttribute, newValue);
		return newFind;
	}
	/* Try to access the find information in the JSON used to build the current
	   modification object. Report an error if the field is not found. If an
	   error is reported, the error count is also incremented. The return value
	   from this function is the find array, if the find array is found. */
	protected static ArrayList<HDLmFind> modFieldFindArray(HDLmEditorTypes editorType, 
			                                                   MutableInt errors, 
			                                                   JsonObject jsonObject, 
			                                                   Set<String> jsonKeys,
			                                                   String name) {
		return modFieldFindArray(editorType, errors, 
				                     jsonObject, jsonKeys, 
				                     name, 
				                     HDLmReportErrors.REPORTERRORS);
	}
	protected static ArrayList<HDLmFind> modFieldFindArray(HDLmEditorTypes editorType, 
			                                                   MutableInt errors, 
			                                                   JsonObject jsonObject, 
			                                                   Set<String> jsonKeys,
		                                                     String name,
		                                                     HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldFindArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldFindArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldFindArray is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldFindArray is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<HDLmFind> newFinds = null;
		boolean fieldFound = false;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(name))
				break;
			fieldFound = true;
			/* Check if the find criteria were passed as a single object or as an array of
			   objects. Both cases can occur. In theory if we only have one find criterion,
			   then it will be just one object. If we have multiple find criteria, we should
			   get an array here. For Java we will always have an array here. */
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonArray())
				break;
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			newFinds = new ArrayList<HDLmFind>();
			for (JsonElement jsonElementEntry : jsonArray) {
				if (jsonElementEntry.isJsonNull())
					continue;
				JsonObject jsonObjectEntry = jsonElementEntry.getAsJsonObject();
				Set<String> jsonKeysEntry = jsonObjectEntry.keySet();
				HDLmFind newFind = modFieldFind(editorType, 
						                            errors, 
						                            jsonObjectEntry, 
						                            jsonKeysEntry, 
						                            reportErrors);
				if (newFind != null) {
					modFindCheck(editorType, 
							         errors, 
							         jsonObjectEntry, 
							         newFind);			
					newFinds.add(newFind);
				}
			}
			break;
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return newFinds;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static Integer modFieldInteger(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     Set<String> jsonKeys,
			                                     String name) {
		return modFieldInteger(editorType, errors, 
				                   jsonObject, jsonKeys, 
				                   name, 
				                   HDLmReportErrors.REPORTERRORS);
	}
	protected static Integer modFieldInteger(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     Set<String> jsonKeys, 
			                                     String name,
			                                     HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldInteger is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldInteger is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldInteger is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldInteger is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		Integer newInteger = null;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				newInteger = jsonElement.getAsInt();
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, 
					                       name, 
					                       "Modification JSON missing field", 
					                       3, 
					                       reportErrors);
		return newInteger;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static HDLmModTypes modFieldModType(HDLmEditorTypes editorType, 
			                                          MutableInt errors, 
			                                          JsonObject jsonObject, 
			                                          Set<String> jsonKeys,
			                                          String name) {
		return modFieldModType(editorType, errors, 
				                   jsonObject, jsonKeys, 
				                   name, 
				                   HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmModTypes modFieldModType(HDLmEditorTypes editorType, 
			                                          MutableInt errors, 
			                                          JsonObject jsonObject, 
			                                          Set<String> jsonKeys,
			                                          String name, 
			                                          HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldModType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldModType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldModType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldModType is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmModTypes modType = HDLmModTypes.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				modType = HDLmModTypes.valueOfString(localString);
				if (modType == HDLmModTypes.NONE) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, 
							                     jsonObject, name, 
							                     value, "Modification JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return modType;
	}
	/* Try to access the node identifier in the JSON used to build the current 
	   modification. Report an error if the field is not found. If an error is
	   reported, the error count is also incremented. The return value from this 
	   function is the node identifier object, if a valid node identifier is found. */ 
	protected static HDLmNodeIden modFieldNodeIden(HDLmEditorTypes editorType, 
			                                           MutableInt errors, 
			                                           JsonObject jsonObject, 
			                                           Set<String> jsonKeys,
			                                           String name) {
		return modFieldNodeIden(editorType, errors, 
				                    jsonObject, jsonKeys, 
				                    name, 
				                    HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmNodeIden modFieldNodeIden(HDLmEditorTypes editorType, 
			                                           MutableInt errors, 
			                                           JsonObject jsonObject, 
			                                           Set<String> jsonKeys,
		                                             String name,
		                                             HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldNodeIden is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldNodeIden is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldNodeIden is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldNodeIden is null";
			throw new NullPointerException(errorText);
		}
    /* Declare and define a few local fields for use below */
		HDLmNodeIden  newNodeIden = null;
		boolean       fieldFound = false;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(name))
				break;
			/* The field in the JSON may be a zero-length string. This is not a real
			   node identifier object. We need to check for, and skip, this case. */
			JsonElement jsonElement = jsonObject.get(name);
			if (jsonElement.isJsonPrimitive() == true) {
				JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
				if (jsonPrimitive.isString() == true) {
					String  jsonString = jsonPrimitive.getAsString();
					if (jsonString.length() == 0)
						fieldFound = true;
						break;
				}				
			}
			/* At this point we check if the node identifier is really an empty
			   object. This can actually happen if the database has an empty
			   object for the node identifier. */
			if (jsonElement.isJsonObject()) { 
				JsonObject    localObject = jsonElement.getAsJsonObject();
				Set<String>   localKeys = localObject.keySet();
				if (localKeys.size() == 0) {
					fieldFound = true;
					newNodeIden = new HDLmNodeIden();
					break;
				}
			}
			fieldFound = true;
			/* We can now try to convert the JSON element into a valid node identifier
			   object. Note that the constructor below can never return a null value.
			   The check for a null value will never be true. */
			newNodeIden = new HDLmNodeIden(jsonElement);
			if (newNodeIden == null) {
				HDLmMod.reportErrorNoValue(editorType, errors, 
                                   jsonObject, name, 
                                   "Modification JSON node identifier is invalid", 
                                   39, reportErrors);			
			}
			break;
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return newNodeIden;
	}	
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static HDLmReportTypes modFieldReportType(HDLmEditorTypes editorType, 
			                                                MutableInt errors, 
			                                                JsonObject jsonObject, 
			                                                Set<String> jsonKeys,
			                                                String name) {
		return modFieldReportType(editorType, errors, 
				                      jsonObject, jsonKeys, 
				                      name, 
				                      HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmReportTypes modFieldReportType(HDLmEditorTypes editorType, 
						                                          MutableInt errors, 
						                                          JsonObject jsonObject, 
						                                          Set<String> jsonKeys,
						                                          String name, 
						                                          HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldReportType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldReportType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldReportType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldReportType is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmReportTypes  modReportType = HDLmReportTypes.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				if (localString.equals("Check website"))
					localString = "CHECKWEBSITE";
				if (localString.equals("Check error"))
					localString = "CHECKERROR";
				modReportType = HDLmReportTypes.valueOfString(localString);
				if (modReportType == HDLmReportTypes.NONE) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, 
							                     jsonObject, name, 
							                     value, "Modification JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return modReportType;
	}	
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static Short  modFieldShort(HDLmEditorTypes editorType, 
			                                  MutableInt errors, 
			                                  JsonObject jsonObject, 
			                                  Set<String> jsonKeys,
			                                  String name) {
		return modFieldShort(editorType, errors, 
				                 jsonObject, jsonKeys, 
				                 name, 
				                 HDLmReportErrors.REPORTERRORS);
	}
	protected static Short  modFieldShort(HDLmEditorTypes editorType, 
			                                  MutableInt errors, 
			                                  JsonObject jsonObject, 
			                                  Set<String> jsonKeys, 
			                                  String name,
			                                  HDLmReportErrors reportErrors) {
		/* Check the values passed by the caller */
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldShort is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldShort is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldShort is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldShort is null";
			throw new NullPointerException(errorText);
		}
		boolean   fieldFound = false;
		Short     newShort = null;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				newShort = jsonElement.getAsShort();
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, 
					                       name, 
					                       "Modification JSON missing field", 
					                       3, 
					                       reportErrors);
		return newShort;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static HDLmPassThruStatus modFieldStatus(HDLmEditorTypes editorType, 
			                                               MutableInt errors, 
			                                               JsonObject jsonObject, 
			                                               Set<String> jsonKeys,
			                                               String name) {
		return modFieldStatus(editorType, errors, 
				                  jsonObject, jsonKeys, 
				                  name, 
				                  HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmPassThruStatus modFieldStatus(HDLmEditorTypes editorType, 
			                                               MutableInt errors, 
			                                               JsonObject jsonObject, 
			                                               Set<String> jsonKeys,
			                                               String name, 
			                                               HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldStatus is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldStatus is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldStatus is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldStatus is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmPassThruStatus  modTreeStatus = HDLmPassThruStatus.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			boolean   localBoolean;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localBoolean = jsonElement.getAsBoolean();
				if (localBoolean)
					modTreeStatus = HDLmPassThruStatus.PASSTHRUOK;
				else
					modTreeStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
				if (modTreeStatus == HDLmPassThruStatus.NONE) {
					String  localString = jsonElement.getAsString();
					String  value = localString;
					HDLmMod.reportErrorValue(editorType, errors, 
							                     jsonObject, name, 
							                     value, "Modification JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return modTreeStatus;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static String modFieldString(HDLmEditorTypes editorType, 
			                                   MutableInt errors, 
			                                   JsonObject jsonObject, 
			                                   Set<String> jsonKeys, 
			                                   String name,
		                                     HDLmWhiteSpace whiteSpaceOK) {
		return modFieldString(editorType, errors, 
				                  jsonObject, jsonKeys, 
				                  name, whiteSpaceOK, 
				                  HDLmReportErrors.REPORTERRORS,
				                  HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	}
	protected static String modFieldString(HDLmEditorTypes editorType, 
			                                   MutableInt errors,
			                                   JsonObject jsonObject, 
  		                                   Set<String> jsonKeys, 
  		                                   String name, 
  		                                   HDLmWhiteSpace whiteSpaceOK, 
  		                                   HDLmReportErrors reportError,
  		                                   HDLmZeroLengthOk zeroLength) {
		if (errors == null) {
		  String  errorText = "Mutable int for errors passed to modFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
		  String  errorText = "Set of keys passed to modFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldString is null";
		  throw new NullPointerException(errorText);
		}
		/* At this point we must deal with a special and very bizarre case. The 
		   rules database calls the path field 'path' (without the quotes). The
		   JavaScript code calls the 'path' field (without the quotes), 'pathValue'
		   (without the quotes). */
		boolean  fieldFound = false;
	  String   newString = null;
    /* Check if we have the field in the JSON */
    if (jsonKeys.contains(name)) {
    	fieldFound = true;
    	JsonElement   jsonElement = jsonObject.get(name);
    	if (!jsonElement.isJsonNull()) {
    		/* LOG.info(name); */
    		/* LOG.info(jsonElement.toString()); */
    		/* LOG.info(jsonObject.toString()); */
    		newString = jsonElement.getAsString();
    		/* Get the length of string, if possible. If the string  is 
    		  a null value, then getting the length is not possible. */
    		int   newStringLength = -1;
				if (newString != null)
					newStringLength = newString.length();
    		/* Generally we can accept a string that is all whitespace
    		   characters. However, in some cases we can not. The check
    		   below rejects string that are all whitespace, if need be.
    		   Note that an empty string is considered to be a whitespace
    		   string by the utility routine invoked below. */
    		if (whiteSpaceOK == HDLmWhiteSpace.WHITESPACENOTOK) {
    		  if (StringUtils.isWhitespace(newString)) {
    		  	/* Check if the new string is a null values. In some
    		  	   case (use mode), this is really OK. */
    		    if (newStringLength != 0 ||
    		    		zeroLength != HDLmZeroLengthOk.ZEROLENGTHOK) {    		  	 
    		  	  newString = null;
    		      fieldFound = false;
    		    }
    		  }  
    		}
    	}
    }
    /* Since we do not have the field in the JSON, report an error */
    if (fieldFound == false) {
      HDLmMod.reportErrorNoValue(editorType, errors, 
      		                       jsonObject, name,
                                 "Modification JSON missing field", 
                                 3, reportError); 
    }
    return newString;
  }
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static ArrayList<String> modFieldStringArray(HDLmEditorTypes editorType, 
			                                                   MutableInt errors, 
			                                                   JsonObject jsonObject, 
			                                                   Set<String> jsonKeys,
			                                                   String name) {
		return modFieldStringArray(editorType, errors, 
				                       jsonObject, jsonKeys, 
				                       name, 
				                       HDLmReportErrors.REPORTERRORS);
	}
	protected static ArrayList<String> modFieldStringArray(HDLmEditorTypes editorType, 
			                                                   MutableInt errors, 
			                                                   JsonObject jsonObject, 
			                                                   Set<String> jsonKeys,
			                                                   String name, 
			                                                   HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<String> newStrings = null;
		boolean fieldFound = false;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(name))
				break;
			/* Show that the field was found */
			fieldFound = true;
			/* Get they array of JSON objects for the strings */
			JsonElement jsonElement = jsonObject.get(name);
			if (jsonElement.isJsonNull())
				break;
			/* Get some values from the input JSON */
			if (!jsonElement.isJsonArray())
				break;
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			/* Build the new string array list */
			newStrings = new ArrayList<String>();
			for (JsonElement jsonElementEntry : jsonArray) {
				if (jsonElementEntry.isJsonNull())
					continue;
				String newString = jsonElementEntry.getAsString();
				if (newString != null)
					newStrings.add(newString);
			}
			break;
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return newStrings;
	}
	/* Check each value in the values array and make sure it is a valid change
     attributes value. The only possible valid change values are either a zero-length 
     string (which mean no attribute changes) or a valid JSON string. Actually we 
     allow an all-whitespace string as well. */ 
	protected static boolean modFieldStringArrayChangeAttrs(ArrayList<String> values,
		                                                      HDLmEditorTypes editorType, 
      																								    MutableInt errors, 
																									        JsonObject jsonObject, 
																									        String name, 
																									        HDLmReportErrors reportErrors) {
		if (values == null) {
		  String  errorText = "Values list passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		if (editorType == null) {
		  String  errorText = "Editor type enum passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		if (errors == null) {
	  String  errorText = "Mutable int for errors passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to modFieldStringArrayChangeAttrs is null";
		  throw new NullPointerException(errorText);
		}
		/* LOG.info("In HDLmMod.modFieldStringArrayChangeAttrs"); */
		/* Declare and define a few values */
		boolean   rv = true;
		/* Check each of the values in the values list */
		int   valuesLength = values.size();
		for (int i = 0; i < valuesLength; i++) {
			/* Get the current string value and get the length of the current value */
			String  curValue = values.get(i);
			int     curValueLength = curValue.length();
			/* If the length of the current value is zero, then we don't have anything
			   to do */
			if (curValueLength == 0)
				continue;		
			if (StringUtils.isWhitespace(curValue))
				continue;
	   /* Try to convert the current value JSON to a JSON object. If this fails,
	      then we do not have a string than can be converted to a JSON object.
	      If this works, then we do have string than can be converted to a JSON
	      object. */
	   JsonParser   parser = new JsonParser();  
	   JsonElement  topNode = parser.parse(curValue); 
	   if (topNode.isJsonObject()) 
	     continue;
		 /* Report that the value is not valid */
		 rv = false;
		 /* LOG.info("HDLmMod.reportErrorValue"); */
		 HDLmMod.reportErrorValue(editorType, errors, 
	                            jsonObject, name, 
	                            curValue, 
	                            "Modification JSON invalid change attributes value", 
	                            4, reportErrors);			
		}
		return rv;
	}
	/* Check each value in the values array and make sure it is a valid remove
	   value. The only possible remove values are yes and no. Note that leading
	   and trailing blanks are always removed from each value. The check for yes
	   or no is caseless by design. */
	protected static boolean modFieldStringArrayRemove(ArrayList<String> values,
			                                               HDLmEditorTypes editorType, 
																										 MutableInt errors, 
																										 JsonObject jsonObject, 
																										 String name, 
																										 HDLmReportErrors reportErrors) {
		if (values == null) {
		  String  errorText = "Values list passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		if (editorType == null) {
		  String  errorText = "Editor type enum passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		if (errors == null) {
	    String  errorText = "Mutable int for errors passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to modFieldStringArrayRemove is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		boolean   rv = true;
		/* Check each of the values in the values list */
		int   valuesLength = values.size();
		for (int i = 0; i < valuesLength; i++) {
			/* Get the current string value and remove any leading and trailing blanks */
			String  curValue = values.get(i);
			curValue = curValue.trim();
			values.set(i, curValue);
			/* Check if the current value is valid */
			if (curValue.equalsIgnoreCase("no"))
				continue;
			if (curValue.equalsIgnoreCase("yes"))
				continue;
			/* Report that the value is not valid */
			rv = false;
			HDLmMod.reportErrorValue(editorType, errors, 
                               jsonObject, name, 
                               curValue, 
                               "Modification JSON invalid remove value", 
                               4, reportErrors);			
		}
		return rv;
	}
	/* Check each value in the values array and make sure it is a valid replace
     value. The only possible value replace values are either a zero-length 
     string (which mean no replacement) or a valid JSON string. This has now
     been changed. We now allow strings that are not valid JSON to be used.
     These strings are the names of saved data values that are (hopefully)
     valid JSON. */ 
	protected static boolean modFieldStringArrayReplace(ArrayList<String> values,
		                                                  HDLmEditorTypes editorType, 
																									    MutableInt errors, 
																									    JsonObject jsonObject, 
																									    String name, 
																									    HDLmReportErrors reportErrors) {
		if (values == null) {
		  String  errorText = "Values list passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		if (editorType == null) {
		  String  errorText = "Editor type enum passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		if (errors == null) {
	   String  errorText = "Mutable int for errors passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to modFieldStringArrayReplace is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		boolean   rv = true;
		/* Check each of the values in the values list */
		int   valuesLength = values.size();
		for (int i = 0; i < valuesLength; i++) {
			/* Get the current string value and get the length of the current value */
			String  curValue = values.get(i);
			int     curValueLength = curValue.length();
			/* If the length of the current value is zero, then we don't have anything
			   to do */
			if (curValueLength == 0)
				continue;		
			/* We now tolerate strings that are not valid JSON. These strings are the 
			   names of saved data values that are (hopefully) valid JSON. */
			if (curValueLength > 0)
				continue;			
	    /* Try to convert the current value JSON to a JSON object. If this fails,
	       then we do not have a string than can be converted to a JSON object.
	       If this works, then we do have string than can be converted to a JSON
	       object. */
	    JsonParser   parser = new JsonParser();  
	    JsonElement  topNode = parser.parse(curValue); 
	    if (topNode.isJsonObject()) 
	      continue;
			/* Report that the value is not valid */
			rv = false;
			HDLmMod.reportErrorValue(editorType, errors, 
	                            jsonObject, name, 
	                            curValue, 
	                            "Modification JSON invalid replace value", 
	                            4, reportErrors);			
		}
		return rv;
	}
	/* Check each value in the values array and make sure it is a valid JavaScript
	   script value */
	protected static boolean  modFieldStringArrayScript(ArrayList<String> values,
			                                                HDLmEditorTypes editorType, 
																										  MutableInt errors, 
																										  JsonObject jsonObject, 
																										  String name, 
																										  HDLmReportErrors reportErrors) {
		if (values == null) {
		  String  errorText = "Values list passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		if (editorType == null) {
		  String  errorText = "Editor type enum passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid in modFieldStringArrayScript");
		}
		if (errors == null) {
	  String  errorText = "Mutable int for errors passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to modFieldStringArrayScript is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		boolean   rv = true;
		/* Check each of the values in the values list */
		int   valuesLength = values.size();
		for (int i = 0; i < valuesLength; i++) {
			/* Get the current string value and remove any leading and trailing blanks */
			String  curValue = values.get(i);
			String  curValueUpdated = HDLmAi.fixWebImproverScript(curValue);
			values.set(i, curValueUpdated);
			/* Check if the current script is valid */
			boolean   scriptValid = HDLmHtml.checkIfJavaScriptValid(curValueUpdated, 
					                                                    HDLmReportErrors.DONTREPORTERRORS);
			/* scriptValid = true; */
			if (scriptValid)
				continue;
			/* Report that the script is not valid */
			rv = false;
			HDLmMod.reportErrorValue(editorType, errors, 
	                           jsonObject, name, 
	                           curValue, 
	                           "Modification JSON invalid script value", 
	                           4, reportErrors);			
		}
		return rv;
	}
	/* Check each value in the values array and make sure it is a valid visit
	   value. The only possible visit values are yes and no. Note that leading
	   and trailing blanks are always removed from each value. The check for yes
	   or no is caseless by design. */
	protected static boolean  modFieldStringArrayVisit(ArrayList<String> values,
			                                               HDLmEditorTypes editorType, 
																										 MutableInt errors, 
																										 JsonObject jsonObject, 
																										 String name, 
																										 HDLmReportErrors reportErrors) {
		if (values == null) {
		  String  errorText = "Values list passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		if (editorType == null) {
		  String  errorText = "Editor type enum passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		if (errors == null) {
	   String  errorText = "Mutable int for errors passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to modFieldStringArrayVisit is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		boolean   rv = true;
		/* Check each of the values in the values list */
		int   valuesLength = values.size();
		for (int i = 0; i < valuesLength; i++) {
			/* Get the current string value and remove any leading and trailing blanks */
			String  curValue = values.get(i);
			curValue = curValue.trim();
			values.set(i, curValue);
			/* Check if the current value is valid */
			if (curValue.equalsIgnoreCase("no"))
				continue;
			if (curValue.equalsIgnoreCase("yes"))
				continue;
			/* Report that the value is not valid */
			rv = false;
			HDLmMod.reportErrorValue(editorType, errors, 
	                            jsonObject, name, 
	                            curValue, 
	                            "Modification JSON invalid visit value", 
	                            4, reportErrors);			
		}
		return rv;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static HDLmTreeTypes modFieldTreeType(HDLmEditorTypes editorType, 
			                                            MutableInt errors, 
			                                            JsonObject jsonObject, 
			                                            Set<String> jsonKeys,
			                                            String name) {
		return modFieldTreeType(editorType, errors, 
				                    jsonObject, jsonKeys, 
				                    name, 
				                    HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmTreeTypes  modFieldTreeType(HDLmEditorTypes editorType, 
			                                             MutableInt errors, 
			                                             JsonObject jsonObject, 
			                                             Set<String> jsonKeys,
			                                             String name, 
			                                             HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmTreeTypes  modTreeType = HDLmTreeTypes.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				modTreeType = HDLmTreeTypes.valueOfString(localString);
				if (modTreeType == HDLmTreeTypes.NONE) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, 
							                     jsonObject, name, 
							                     value, "Modification JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return modTreeType;
	}
	/* Try to access a field in the JSON used to build the current modification
	   object. Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function is
	   the value of the field, if the field is found. */
	protected static ZonedDateTime modFieldZonedDateTime(HDLmEditorTypes editorType, 
			                                                 MutableInt errors, 
			                                                 JsonObject jsonObject, 
			                                                 Set<String> jsonKeys,
			                                                 String name) {
		return modFieldZonedDateTime(editorType, errors, 
				                         jsonObject, jsonKeys, 
				                         name, 
				                         HDLmReportErrors.REPORTERRORS);
	}
	protected static ZonedDateTime modFieldZonedDateTime(HDLmEditorTypes editorType, 
			                                                 MutableInt errors, 
			                                                 JsonObject jsonObject, 
			                                                 Set<String> jsonKeys,
			                                                 String name, 
			                                                 HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFieldZonedDateTime is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFieldZonedDateTime is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to modFieldZonedDateTime is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modFieldZonedDateTime is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		ZonedDateTime  modZonedDateTime = null; 
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String  localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				modZonedDateTime = ZonedDateTime.parse(localString);
				if (modZonedDateTime == null) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, 
							                     jsonObject, name, 
							                     value, "Modification JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON missing field", 
					                       3, reportErrors);
		return modZonedDateTime;
	}
	/* Make sure that at least one set of locate information was actually set. Of
	   course, only one set of locate information can be (correctly) set. An HTML
	   field can be located using a CSS selector, an XPath string, or a set of
	   finds. Note that the set of finds can be just one find. Two or more finds are
	   not required. However, one of these must be set. It is an error if all of 
	   them are not set. */
	protected static void modFindCheck(HDLmEditorTypes editorType, 
			                               MutableInt errors, 
			                               JsonObject jsonObject,  
			                               HDLmFind findInfo) {
		modFindCheck(editorType, errors, 
				         jsonObject, findInfo, 
				         HDLmReportErrors.REPORTERRORS);
	}
	protected static void modFindCheck(HDLmEditorTypes editorType, 
			                               MutableInt errors, 
			                               JsonObject jsonObject, 
	                                 	 HDLmFind findInfo, 
	                                 	 HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modFindCheck is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modFindCheck is null";
			throw new NullPointerException(errorText);
		}
		if (findInfo == null) {
			String  errorText = "Find object passed to modFindCheck is null";
			throw new NullPointerException(errorText);
		}
    /* Either tag or attribute information must be provided */
		if (findInfo.getTag() == null && 
				findInfo.getAttribute() == null) {
			String  errorText = "No find tag or attribute information provided";
			HDLmMod.reportError(editorType, errors, 
					                jsonObject, errorText, 
					                23, reportErrors);
		}
		/* If we have an attribute value we must also have a value for the 
		   attribute */
		if (findInfo.getAttribute() != null &&
				findInfo.getValue()     == null) {
			String  errorText = "Find attribute name found without value information";
			if (reportErrors == HDLmReportErrors.REPORTERRORS)
			  HDLmMod.reportError(editorType, errors, 
			  		                jsonObject, errorText, 
			  		                23, reportErrors);
		}
	}	
	/* Make sure that at least one set of locate information was actually set. Of
	   course, only one set of locate information can be (correctly) set. An HTML
	   field can be located using a CSS selector, an XPath string, or a set of
	   finds. Note that the set of finds can be just one find. Two or more finds are
	   not required. However, one of these must be set. It is an error if all of 
	   them are not set. */
	protected static void modLocateCheck(HDLmEditorTypes editorType, 
			                                 HDLmModTypes modType,
			                                 MutableInt errors, 
			                                 JsonObject jsonObject, 
			                                 String cssInfo, 
			                                 String xpathInfo,
			                                 ArrayList<HDLmFind> findInfo,
			                                 HDLmNodeIden nodeIden) {
		modLocateCheck(editorType,
				           modType,
				           errors, 
				           jsonObject, cssInfo, 
				           xpathInfo, findInfo, 
				           nodeIden,
				           HDLmReportErrors.REPORTERRORS);
	}
	protected static void modLocateCheck(HDLmEditorTypes editorType, 
			                                 HDLmModTypes modType,
			                                 MutableInt errors, 
			                                 JsonObject jsonObject, 
			                                 String cssInfo, 
			                                 String xpathInfo,
	                                  	 ArrayList<HDLmFind> findInfo,
	                                  	 HDLmNodeIden nodeIden,
	                                  	 HDLmReportErrors reportErrors) {
	  /* Check if the editor type is set or null */
		if (editorType == null) {
			String  errorText = "Editor type passed to modLocateCheck is null";
			throw new NullPointerException(errorText);
    }
    /* Check if the editor type is invalid */
    if (editorType == HDLmEditorTypes.NONE) {
			String  errorText = "Editor type passed to modLocateCheck is invalid";
			throw new IllegalArgumentException(errorText);
    }
	  /* Check if the modification type is set or null */
		if (modType == null) {
			String  errorText = "Modification type passed to modLocateCheck is null";
			throw new NullPointerException(errorText);
    }
    /* Check if the modification type is invalid */
    if (modType == HDLmModTypes.NONE) {
			String  errorText = "Modification type passed to modLocateCheck is invalid";
			throw new IllegalArgumentException(errorText);
    }
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modLocateCheck is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modLocateCheck is null";
			throw new NullPointerException(errorText);
		}
		/* Set the required locate count based on the current modification 
		   type. The required locate count is generally one, except for 
		   visit rules for which the required locate count is zero. */
		int   requiredLocateCount = 1;
		if (modType == HDLmModTypes.VISIT)
		  requiredLocateCount = 0;
		int locateCount = 0;
		/* Check if the CSS selector information is set */
		if (cssInfo != null && !StringUtils.isWhitespace(cssInfo))
			locateCount++;
		/* Check if the XPath search information string is set */
		if (xpathInfo != null && !StringUtils.isWhitespace(xpathInfo))
			locateCount++;
		/* Check if at least one find is set */
		if (findInfo != null && findInfo.size() > 0)
			locateCount++;
		/* Check if the node identifier can be used */
		if (nodeIden != null && nodeIden.isUsable())
			locateCount++;
		/* Check if we have too many types of locate information */
		if (locateCount > requiredLocateCount) {
			String  errorText = "Too much location information provided";
			HDLmMod.reportError(editorType, errors, 
					                jsonObject, errorText, 
					                27, reportErrors);
		}
		if (locateCount == 0 && requiredLocateCount != 0) {
			String  errorText = "No location information provided";
			HDLmMod.reportError(editorType, errors, 
					                jsonObject, errorText, 
					                23, reportErrors);
		}
	}
	/* The size value passed by the caller may be a percentage value or it may be a
	   pixel value. If the size value ends with a percentage sign, then the size
	   value is presumed to be a percentage value. If the size value ends with the
	   letters "px", then the size value is presumed to be a pixel value. If the
	   size value does not end with either a percentage sign or the letters "px",
	   then it is assumed to be a pixel. The numeric value and the suffix are
	   returned to the caller using an array. Note that a special value of "auto" is
	   also supported. The special value of "auto" has no suffix can not be
	   modified. */
	protected static ArrayList<String> modPercentPixel(String size) {
		if (size == null) {
			String  errorText = "Size string passed to modPercentPixel is null";
			throw new NullPointerException(errorText);
		}
		int sizeLen = size.length();
		String sizeLowerCase = size.toLowerCase();
		ArrayList<String> outArray = new ArrayList<String>();
		/* Check for the special value of "auto". Note that the check will work with any
		   case. The value stored in the output array is always lower case. */
		if (sizeLowerCase.equals("auto")) {
			outArray.add("auto");
			outArray.add("");
			return outArray;
		}
		/* Check if the size is just a numeric value with no suffix */
		if (StringUtils.isNumeric(size)) {
			outArray.add(size);
			outArray.add("px");
			return outArray;
		}
		/* Check for a percent sign suffix */
		if (sizeLen >= 1 && size.charAt(sizeLen - 1) == '%') {
			outArray.add(size.substring(0, sizeLen - 1));
			outArray.add("%");
			return outArray;
		}
		/* Check for a pixel suffix */
		if (sizeLen >= 2) {
			String suffix = size.substring(sizeLen - 2, sizeLen);
			String suffixLower = suffix.toLowerCase();
			if (suffixLower.equals("px")) {
				outArray.add(size.substring(0, sizeLen - 2));
				outArray.add("px");
				return outArray;
			}
		}
		return outArray;
	}
  /* Check if a field sufix is valid or not. Report an error if the field
     suffix is invalid. The caller provides the list of valid suffix values.
     Note that a NULL value is always considered to be invalid and will be
     reported in all cases. */
	protected static void modSuffixCheck(HDLmEditorTypes editorType, 
			                                 MutableInt errors, 
			                                 JsonObject jsonObject, 
			                                 String name,
                                       ArrayList<String> validArray, 
                                       String suffixValue) {
		modSuffixCheck(editorType, errors, 
				           jsonObject, name, 
				           validArray, suffixValue, 
				           HDLmReportErrors.REPORTERRORS);
	}
	protected static void modSuffixCheck(HDLmEditorTypes editorType, 
			                                 MutableInt errors, 
			                                 JsonObject jsonObject, 
			                                 String name,
			                                 ArrayList<String> validArray, 
			                                 String suffixValue, 
			                                 HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to modSuffixCheck is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to modSuffixCheck is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to modSuffixCheck is null";
			throw new NullPointerException(errorText);
		}
		if (validArray == null) {
			String  errorText = "Array of valid suffix values passed to modSuffixCheck is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the field actually has a value of not. A NULL value is
	     not considered to be a valid value. */
	  if (suffixValue == null) {
	    HDLmMod.reportErrorNoValue(editorType, errors, 
	    		                       jsonObject, name,
	                               "Modification JSON field NULL", 
	                               5, reportErrors);
	    return;
	  }
	  /* Check if the suffix value provided by the caller is not one of the
	     allowed values. This should not happen, but might happen anyway. */
	  if (!validArray.contains(suffixValue)) {
	    HDLmMod.reportErrorValue(editorType, errors, 
	    		                     jsonObject, name, 
	    		                     suffixValue,
	                             "Modification JSON suffix value is invalid", 
	                             4, reportErrors);
	    return;
	  }
	}  
	/* The method below processes just one set of details (really just
	   one modification). The JSON element is converted to a JSON object
	   and various changes are mode. */  
	protected static void processJsonMod(JsonElement jsonElement) {		
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonMod is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonMod is not a JSON object");
		/* Get the current modification type from the current modification */
		String  typeString = HDLmJson.getJsonString(jsonElement, "type");
		if (typeString == null)
			HDLmAssertAction(false, "Type value can not be obtained from the JSON element passed to processJsonMod");
		HDLmModTypes  typeValue = HDLmModTypes.valueOf(typeString);
		typeString = typeString.toLowerCase();
		/* Get a few more values from the details (really a modification rule) */
		String  nameString = HDLmJson.getJsonString(jsonElement, "name");
		if (nameString == null)
			nameString = "";
		String  extraString = HDLmJson.getJsonString(jsonElement, "extra");
		if (extraString == null)
			extraString = "";
		Boolean   enabledBoolean = HDLmJson.getJsonBoolean(jsonElement, "enabled");
		if (enabledBoolean == null)
			enabledBoolean = false;
		String  pathValueString = HDLmJson.getJsonString(jsonElement, "path");
		if (pathValueString == null ||
				pathValueString.equals(""))
			pathValueString = "/";
		String  cssString = HDLmJson.getJsonString(jsonElement, "cssSelector");
		if (cssString == null)
			cssString = "";
		String  xPathString = HDLmJson.getJsonString(jsonElement, "xPath");
		if (xPathString == null)
			xPathString = "";
		JsonElement   findsElement = HDLmJson.getJsonValue(jsonElement, "finds");
		if (findsElement == null)
			findsElement = new JsonArray();
		JsonElement   nodeIdenElement = HDLmJson.getJsonValue(jsonElement, "nodeIden");
		if (nodeIdenElement == null)
			nodeIdenElement = new JsonObject();	
		else {
			if (nodeIdenElement.isJsonPrimitive()) {
				JsonPrimitive   jsonPrimitive = nodeIdenElement.getAsJsonPrimitive();
				String  nodeIdenString = jsonPrimitive.getAsString();
			}
			else
				HDLmNodeIden.processJsonNodeIden(nodeIdenElement);
		}
		boolean   parameterNumberFound = true;
		Integer   parameterNumberInteger = HDLmJson.getJsonInteger(jsonElement, "parameter");
		if (parameterNumberInteger == null) {
			parameterNumberFound = false;
		}
		Boolean   updatedBoolean = HDLmJson.getJsonBoolean(jsonElement, "updated");
		if (updatedBoolean == null)
			updatedBoolean = false; 
		/* Remove a set of keys from the current JSON object */
		HDLmJson.removeJsonKey(jsonElement, "cssSelector");
		HDLmJson.removeJsonKey(jsonElement, "finds");
		HDLmJson.removeJsonKey(jsonElement, "pathRe");
		HDLmJson.removeJsonKey(jsonElement, "valuesCount");
		HDLmJson.removeJsonKey(jsonElement, "parameter");
		HDLmJson.removeJsonKey(jsonElement, "parameterNumber");
		HDLmJson.removeJsonKey(jsonElement, "xPath");
		HDLmJson.removeJsonKey(jsonElement, "nodeIden");
		HDLmJson.removeJsonKey(jsonElement, "pathValue");
		HDLmJson.removeJsonKey(jsonElement, "errorCount");
		/* Put the values back in the JSON object in a specific order */
		HDLmJson.setJsonString(jsonElement, "cssselector", cssString);
		HDLmJson.setJsonString(jsonElement, "extra", extraString);
		HDLmJson.setJsonBoolean(jsonElement, "enabled", enabledBoolean);		
		HDLmJson.setJsonValue(jsonElement, "find", findsElement);
		HDLmJson.setJsonString(jsonElement, "name", nameString);
		HDLmJson.setJsonValue(jsonElement, "nodeiden", nodeIdenElement);
		if (parameterNumberInteger != null)
		  HDLmJson.setJsonInteger(jsonElement, "parameter", parameterNumberInteger);
		HDLmJson.setJsonString(jsonElement, "path", pathValueString);
		HDLmJson.setJsonString(jsonElement, "type", typeString);
		HDLmJson.setJsonString(jsonElement, "xpath", xPathString);
		HDLmJson.setJsonBoolean(jsonElement, "updated", updatedBoolean);
		/* Get the correct name of the array field (if any) for the current type */
    String  arrayName = HDLmMod.getArrayName(typeValue);
    if (arrayName != null) {
    	JsonElement   jsonElementValues = HDLmJson.getJsonValue(jsonElement, "values");
    	HDLmJson.removeJsonKey(jsonElement, "values");
    	HDLmJson.setJsonValue(jsonElement, arrayName, jsonElementValues);    	
    }		
	}
	/* Check if a field is in range or not. Report an error if the field is out of
	   range. The range includes an optional minimum value and an optional maximum
	   value. Both range values are checked if need be. Note that a null value is
	   always considered to be invalid and will be reported in all cases. */
	protected static void rangeField(HDLmEditorTypes editorType, 
			                             MutableInt errors, 
			                             JsonObject jsonObject, 
			                             String name, 
			                             Integer value, 
			                             Integer min,
			                             Integer max) {
		rangeField(editorType, errors, 
				       jsonObject, name, 
				       value, min, 
				       max, 
				       HDLmReportErrors.REPORTERRORS);
	}
	protected static void rangeField(HDLmEditorTypes editorType, 
			                             MutableInt errors, 
			                             JsonObject jsonObject, 
			                             String name, 
			                             Integer value, 
			                             Integer min,
			                             Integer max, 
			                             HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to rangeField is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to rangeField is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to rangeField is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the field actually has a value of not. A null value is not
		   considered to be a valid value. */
		if (value == null) {
			HDLmMod.reportErrorNoValue(editorType, errors, 
					                       jsonObject, name, 
					                       "Modification JSON field null", 5, 
					                       reportErrors);
			return;
		}
		/* Check if we actually have a minimum value and if the passed value is less
		   than the minimum value */
		if (min != null && value < min) {
			String valueString = value.toString();
			HDLmMod.reportErrorValue(editorType, errors, 
					                     jsonObject, name, 
					                     valueString, "Modification JSON too low", 
					                     6, reportErrors);
			return;
		}
		/* Check if we actually have a maximum value and if the passed value is greater
		   than the maximum value */
		if (max != null && value > max) {
			String valueString = value.toString();
			HDLmMod.reportErrorValue(editorType, errors, 
					                     jsonObject, name, 
					                     valueString, "Modification JSON too high", 
					                     7, reportErrors);
			return;
		}
	} 
	/* The next routine replaces some characters in a string with
     other characters. The replacement process produces a string
     that can be used as an attribute name (among other things). */
  protected static String  replaceInString(String inStr) {
    /* The characters used below are in Laotian letters. These 
       values were chosen because they are in the BMP, obscure
       (to most English speakers), and do not cause exceptions. */   	
    /* Handle a few uppercase letters */
    inStr = inStr.replaceAll("A", "\u0e81");
    inStr = inStr.replaceAll("B", "\u0e82");
    inStr = inStr.replaceAll("C", "\u0e84");
    inStr = inStr.replaceAll("D", "\u0e87");
    inStr = inStr.replaceAll("E", "\u0e88");
    inStr = inStr.replaceAll("F", "\u0e8a");
    inStr = inStr.replaceAll("G", "\u0e8d");
    inStr = inStr.replaceAll("H", "\u0e94");
    inStr = inStr.replaceAll("I", "\u0e97");
    inStr = inStr.replaceAll("J", "\u0e99");
    inStr = inStr.replaceAll("K", "\u0e9f");
    inStr = inStr.replaceAll("L", "\u0ea1");
    inStr = inStr.replaceAll("M", "\u0ea3");
    inStr = inStr.replaceAll("N", "\u0ea5");
    inStr = inStr.replaceAll("O", "\u0ea7");
    inStr = inStr.replaceAll("P", "\u0eaa");
    inStr = inStr.replaceAll("Q", "\u0eab"); 
    inStr = inStr.replaceAll("R", "\u0ead"); 
    inStr = inStr.replaceAll("S", "\u0eb9"); 
    inStr = inStr.replaceAll("T", "\u0ebb"); 
    inStr = inStr.replaceAll("U", "\u0ebd"); 
    inStr = inStr.replaceAll("V", "\u0ec0");
    inStr = inStr.replaceAll("W", "\u0ec4");
    inStr = inStr.replaceAll("X", "\u0ec6");
    inStr = inStr.replaceAll("Y", "\u0ec8");
    inStr = inStr.replaceAll("Z", "\u0ecd");
    /* Handle a few other characters */
    inStr = inStr.replaceAll("\\s", "\u0ed0");
    inStr = inStr.replaceAll("\\$", "\u0ed1");
    inStr = inStr.replaceAll("\\.", "\u0ed2");
    inStr = inStr.replaceAll("\\/", "\u0ed3");
    inStr = inStr.replaceAll("\\(", "\u0ed4");
    inStr = inStr.replaceAll("\\)", "\u0ed5");
    /* Return the modified string to the caller */   
    return inStr;
  } 
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller does not provide any actual value as part of calling this
	   method. */
	protected static String reportError(HDLmEditorTypes editorType, 
			                                MutableInt errors, 
			                                JsonObject jsonObject, 
			                                String  errorText, 
			                                int errorNumber) {
    return reportError(editorType, errors, 
    		               jsonObject, errorText, 
    		               errorNumber, 
    		               HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportError(HDLmEditorTypes editorType, 
			                                MutableInt errors, 
			                                JsonObject jsonObject, 
			                                String  errorText, 
			                                int errorNumber,
			                                HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorNullText = "Mutable int for errors passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		/* Increment the error count */
		errors.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller does not provide the actual value as part of calling this
	   method. */
	protected static String reportErrorNoValue(HDLmEditorTypes editorType, 
			                                       MutableInt errors, 
			                                       JsonObject jsonObject, 
			                                       String name, 
			                                       String  errorText,
			                                       int errorNumber) {
		return reportErrorNoValue(editorType, errors, 
				                      jsonObject, name, 
				                      errorText, errorNumber, 
				                      HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportErrorNoValue(HDLmEditorTypes editorType, 
			                                       MutableInt errors, 
			                                       JsonObject jsonObject, 
			                                       String name, 
			                                       String  errorText,
			                                       int errorNumber, 
			                                       HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorNullText = "Mutable int for errors passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (name == null) {
			String  errorNullText = "Field name string passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		/* Increment the error count */
		errors.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the field name */
		errorText += " name (";
		errorText += name;
		errorText += ")";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller provides the actual value as part of calling this method. */
	protected static String reportErrorValue(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     String name, 
			                                     String value,
			                                     String  errorText, 
			                                     int errorNumber) {
		return reportErrorValue(editorType, errors, 
				                    jsonObject, 
				                    name, value, 
				                    errorText, errorNumber, 
				                    HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportErrorValue(HDLmEditorTypes editorType, 
			                                     MutableInt errors, 
			                                     JsonObject jsonObject, 
			                                     String name, 
			                                     String value,
			                                     String  errorText, 
			                                     int errorNumber, 
			                                     HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorNullText = "Mutable int for errors passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (name == null) {
			String  errorNullText = "Field name string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (value == null) {
			String  errorNullText = "Value string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		/* Increment the error count */
		errors.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the field name */
		errorText += " name (";
		errorText += name;
		errorText += ")";
		/* Add the invalid field value */
		errorText += " value (";
		errorText += value;
		errorText += ")";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an invalid JSON field. This routine always report an error. This
	   routine always increments the error count. The caller provides the field
	   name, the field value, and the JSON. */
	protected static String reportField(HDLmEditorTypes editorType, 
			                                MutableInt errors, 
			                                JsonObject jsonObject, 
			                                String name, 
			                                String value) {
	  return reportField(editorType, 
	  		               errors, 
	  		               jsonObject, 
	  		               name, 
	  		               value, 
	  		               HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportField(HDLmEditorTypes editorType, 
			                                MutableInt errors, 
			                                JsonObject jsonObject, 
			                                String name, 
			                                String value,
			                                HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to reportField is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to reportField is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Field name string passed to reportField is null";
			throw new NullPointerException(errorText);
		}
		if (value == null) {
			String  errorText = "Value string passed to reportField is null";
			throw new NullPointerException(errorText);
		}
		return HDLmMod.reportErrorValue(editorType, 
				                            errors, 
				                            jsonObject, 
				                            name, 
				                            value, 
				                            "Modification JSON invalid field", 
				                            4,
				                            reportErrors);
	}
	/* Do all of the work needed to save a web page change. The change is saved in
	   an array of changes. Note that the array used to save changes is passed by
	   reference, not by value. The array is actually changed by this routine. */
	protected static void saveChanges(ArrayList<HDLmSavedChange> savedChangesArray,
			                              Integer paramNumber,
			                              Double paramValue, 
			                              String modName, 
			                              String modPathValue, 
			                              HDLmModTypes modType, 
			                              String oldValue, 
			                              String newValue) {
		/* We only check if the array reference is null here, because all of the
		   other values can be null. That means the saved change instance can end
		   up with a lot of null values in it. */
		if (savedChangesArray == null) {
			String  errorText = "Saved changes array passed to saveChanges is null";
			throw new NullPointerException(errorText);
		}
		HDLmSavedChange saveObj = new HDLmSavedChange();
		saveObj.setParameterNumber(paramNumber);
		/* The parameter value should be handled as a floating-point value, not a
		   string. The code below, converts the value passed by the caller (which may be
		   a string) to a floating-point value. */
		saveObj.setParameterValue(paramValue);
		saveObj.setModName(modName);
		saveObj.setModPathValue(modPathValue);
		saveObj.setModType(modType);
		saveObj.setOldValue(oldValue);
		saveObj.setNewValue(newValue);
		savedChangesArray.add(saveObj);
	}
	/* Set the comments (comments string) for a modification */
	protected void setComments(String newComments) {
		this.comments = newComments;
	}
	/* Set the created field for a modification */
	protected void setCreated(final Instant newCreated) {
		this.created = newCreated;
	}
	/* Set the created field for a modification to a null value */
	protected void setCreatedNull() {
		this.created = null;
	}
	/* Set the CSS Selector for a modification */
	protected void setCssSelector(String newCssSelector) {
		this.cssSelector = newCssSelector;
	}
	/* Set the dummy created field for a modification. Note
	   that this field is a string. */
	protected void setDummyCreated(final String newDummyCreated) {
		this.dummyCreated = newDummyCreated;
	}
	/* Set the dummy last modified field for a modification. Note
     that this field is a string. */
  protected void setDummyLastModified(final String newDummyLastModified) {
	  this.dummyLastModified = newDummyLastModified;
  }
	/* Set the dummy pass-through field for a modification */
	protected void setDummyPassThru(Boolean newDummyPassThru) {
		this.dummyPassThru = newDummyPassThru;
	}
	/* Set the dummy table field for a modification */
	protected void setDummyTable(String newDummyTable) {
		this.dummyTable = newDummyTable;
	}
	/* Set the dummy type field for a modification */
	protected void setDummyType(String newDummyType) {
		this.dummyType = newDummyType;
	}
	/* Set the dummy updated field for a modification */
	protected void setDummyUpdated(Boolean newDummyUpdated) {
		this.dummyUpdated = newDummyUpdated;
	}
	/* Set the enabled flag for a modification */
	protected void setEnabled(Boolean newEnabled) {
		this.enabled = newEnabled;
	}
	/* set the modification extra information value */
	protected void setExtra(String newExtra) {
	  this.extra = newExtra;;
	}
	/* Set the finds associated with a modification */
	protected void setFinds(ArrayList<HDLmFind> newFinds) {
		this.finds = newFinds;
	}
	/* Set the extra field, if it is not set */
	protected void setIfNotSetExtra(String newExtra) {
		/* Check if the new extra value passed by the caller is null */
		if (newExtra == null) {
			String  errorText = "New exta string value passed to setIfNotSetExtra is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new extra value, if the old value is not set */
		if (this.extra == null ||
			  this.extra.equals(""))
		  this.extra = newExtra;
	}
	/* Set the name field, if it is not set */
	protected void setIfNotSetName(String newName) {
		/* Check if the new name value passed by the caller is null */
		if (newName == null) {
			String  errorText = "New name string value passed to setIfNotSetName is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new name value, if the old value is not set */
		if (this.name == null ||
			  this.name.equals(""))
		  this.name = newName;
	}
	/* Set the node identifier field, if it is not set */
	protected void setIfNotSetNodeIden(HDLmNodeIden newNodeIden) {
		/* Check if the new node identifier value passed by the caller is null */
		if (newNodeIden == null) {
			String  errorText = "New node identifier value passed to setIfNotSetNodeIden is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new node identifier value, if the old value is not set */
		if (this.nodeIden == null) 
			this.nodeIden = newNodeIden;
	}
	/* Set the parameter number field, if it is not set */
	protected void setIfNotSetParameterNumber(Integer newParameterNumber) {
		/* Check if the new parameter number passed by the caller is null */
		if (newParameterNumber == null) {
			String  errorText = "New parameter number value passed to setIfNotSetParameterNumber is null";
			throw new NullPointerException(errorText);
		}
		/* Set the parameter number value, if the old value is not set */
		if (this.parameter == null) 
			this.parameter = newParameterNumber;
	}
	/* Set the path value field, if it is not set */
	protected void setIfNotSetPathValue(String newPathValue) {
		/* Check if the new path value value passed by the caller is null */
		if (newPathValue == null) {
			String  errorText = "New path value string value passed to setIfNotSetPathValue is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new path value value, if the old value is not set */
		if (this.pathValue == null ||
			  this.pathValue.equals(""))
		  this.pathValue = newPathValue;
	}
	/* The next routine sets a few time values in a modification object.
	   This routine sets the created, last modified times, if they are
	   not already set. */ 
	protected void         setIfNotSetTimes() {
		/* Set the created field, if it is not set */
		if (this.created == null)
			this.created = Instant.now();
		/* Set the last modified field, if it is not set */
		if (this.lastModified == null)
			this.lastModified = Instant.now();
	}
	/* Set the type field, if it is not set */
	protected void setIfNotSetType(String newType) {
		/* Check if the new type value passed by the caller is null */
		if (newType == null) {
			String  errorText = "New type string value passed to setIfNotSetType is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new type value, if the old value is not set */
		if (this.type == null              ||
				this.type == HDLmModTypes.NONE ||
			  this.type.toString().equals(""))
		  this.type = HDLmModTypes.valueOf(newType.toUpperCase());
	}
	/* Set the values field, if it is not set */
	protected void setIfNotSetValues(ArrayList<String> newValues) {
		/* Check if the new values array list passed by the caller is null */
		if (newValues == null) {
			String  errorText = "New values array list value passed to setIfNotSetValues is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new values reference, if the old values reference is not set */
		if (this.values == null ||
				this.values.size() == 0)  
		  this.values = newValues; 
	}
	/* Set the last modified field for a modification */
	protected void setLastModified(final Instant newLastModified) {
		this.lastModified = newLastModified;
	}
	/* Set the last modified field for a modification to a null value */
	protected void setLastModifiedNull() {
		this.lastModified = null;
	}
	/* Set the modification name value */
	protected void setName(String newName) {
		this.name = newName;
	}
	/* Set the modification node identifier value */
	protected void setNodeIden(HDLmNodeIden newNodeIden) {
		this.nodeIden = newNodeIden;
	}
	/* Set the path value string for a modification */
	protected void setPathValue(String newPathValue) {
		this.pathValue = newPathValue;
	}
	/* Set the path value match object reference for a modification */
	protected void setPathValueMatch(HDLmMatch newPathValueMatch) {
		this.pathMatch = newPathValueMatch;
	}
	/* Set the path value regex flag for a modification */
	protected void setPathValueRe(Boolean newPathValueRe) {
		this.pathre = newPathValueRe;
	}
	/* Set the modification type value */
	protected void setType(HDLmModTypes newType) {
		this.type = newType;
	}
	/* Set the modification value */
	protected void setValue(String newValue) {
		this.value = newValue;
	}
	/* Set the modification value suffix */
	protected void setValueSuffix(String newSuffix) {
	  this.valueSuffix = newSuffix;
	}
	/* Set the modification values */
	protected void setValues(ArrayList<String> newValues) {
		this.values = newValues;
	}
	/* Set the modification values count */
	protected void setValuesCount(int newValuesCount) {
		this.valuesCount = newValuesCount;
	}
	/* Set the modification XPath value */
	protected void setXPath(String newXPath) {
		this.xPath = newXPath;
	}
	/* The next method converts a JSON object to a JSON string and truncates the
	   string if need be */
	protected static String truncateJson(JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object passed to truncateJson is null";
			throw new NullPointerException(errorText);
		}
		String jsonStr = jsonObject.toString();
		return HDLmString.strTruncate(jsonStr, HDLmDefines.getNumber("HDLMMAXJSONERRORLEN"));
	}	
}