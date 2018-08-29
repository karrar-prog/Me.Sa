package com.example.domka.menhajAlsaleheen.app.Model.ThematicDivision;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by
 * domka on 2017-04-23.
 */
public class ThematicDevision
{
    private Integer ID;
    private Integer parent_ID;
    private String title;
    private String into;
    private String type;
    private String note;
    private String isOK = "no";

    public ThematicDevision(Integer ID, Integer parent_ID, String title, String into, String type, String note)
    {
        this.ID = ID;
        this.parent_ID = parent_ID;
        this.title = title;
        this.into = into;
        this.type = type;
        this.note = note;
    }

    public ThematicDevision(Integer ID)
    {
        this.ID = ID;
    }

    public ThematicDevision()
    {
    }

    private String getParentsID(Context context, Integer childId, String parents)
    {
        // complete
        Table table;
        String params[] = {String.valueOf(childId)};
        //get parent ID to this child
        String sql = "SELECT parent_ID FROM thematic_division WHERE ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        //break condition
        if (table.ROWS > 0 & isOK.equals("no"))
        {
            parents += childId + "|";
            return String.valueOf(getParentsID(context, Integer.valueOf(table.get(0, "parent_ID")), parents));
        }
        else
        {
            isOK = "yes";
            return parents;
        }
    }

    public Integer[] get_parents_IDS(Context context, Integer childId, String parents_ID)
    {
        String parents;
        //get IDs as string from re_function
        parents = getParentsID(context, childId, parents_ID);

        //calculate number of IDs parents
        Integer c_char = 0;
        for (int j = 0; j < parents.length(); j++)
        {
            if ((String.valueOf(parents.charAt(j)).equals("|")))
            {
                c_char += 1;
            }
        }
        int c = parents.length() - c_char;
        //create array IDs holder
        Integer[] ids = new Integer[c];
        //split IDs ind insert into array
        Integer count = 0;
        String num = "";
        for (int i = 0; i < parents.length(); i++)
        {
            if (!(String.valueOf(parents.charAt(i)).equals("|")))
            {
                num = num + parents.charAt(i);
                //casting String ID to Integer % set value in one index of Array
                ids[count] = Integer.valueOf(num);
                count = count + 1;
            }
            else
            {
                num = "";
            }
        }
        return ids;
    }

    public Integer[] getChildeID(Context context, Integer parent)
    {
        Table table;
        String params[] = {String.valueOf(parent)};
        String sql = "SELECT ID FROM thematic_division WHERE parent_ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        Integer c = table.ROWS;
        Integer[] childs = new Integer[c];
        for (int i = 0; i < table.ROWS; i++)
        {
            childs[i] = Integer.valueOf(table.get(i, "ID"));
        }
        return childs;
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public Integer getParent_ID()
    {
        return parent_ID;
    }

    public void setParent_ID(Integer parent_ID)
    {
        this.parent_ID = parent_ID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getInto()
    {
        return into;
    }

    public void setInto(String into)
    {
        this.into = into;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }
}
