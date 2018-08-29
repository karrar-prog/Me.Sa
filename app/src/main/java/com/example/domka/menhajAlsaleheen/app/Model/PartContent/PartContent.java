package com.example.domka.menhajAlsaleheen.app.Model.PartContent;

import android.content.Context;

import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.Table;

/**
 * Created by domka on 2017-04-23.
 */
public class PartContent
{
    private Integer ID;
    private Integer part_ID;
    private Integer page_no;
    private String content;
    private String footnote;
    private String note;
    private String favorite;
    private String image;
    private String image_path;

    public PartContent(Integer ID, Integer part_ID, Integer page_no, String content, String footnote, String note, String favorite, String image, String image_path)
    {
        this.ID = ID;
        this.part_ID = part_ID;
        this.page_no = page_no;
        this.content = content;
        this.footnote = footnote;
        this.note = note;
        this.favorite = favorite;
        this.image = image;
        this.image_path = image_path;
    }

    public PartContent(Integer part_ID)
    {
        this.part_ID = part_ID;
    }

    public PartContent()
    {
    }

    public Table getAllpart_content(Context context, Integer part_ID)
    {
        Table table;
        String params[] = {String.valueOf(part_ID)};
        String sql = "SELECT * FROM part_content WHERE part_ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        return table;
    }

    public String get_page_content(Context context, Integer part_ID, Integer page_no)
    {
        Table table;
        String params[] = {String.valueOf(part_ID), String.valueOf(page_no)};
        String sql = "SELECT * FROM part_content WHERE part_ID = ? and page_no = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        return table.get(0,"content");
    }


    public void setFavorite(Context context, Integer part_ID, Integer page_no)
    {
        String params[] = {String.valueOf(part_ID), String.valueOf(page_no)};
        String sql = "UPDATE part_content SET favorite ='yes' WHERE part_ID = ? and page_no = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.select(sql, params);
        db.close();
    }

    public void unsetFavorite(Context context, Integer part_ID, Integer page_no)
    {
        String params[] = {String.valueOf(part_ID), String.valueOf(page_no)};
        String sql = "UPDATE part_content SET favorite = 'no' WHERE part_ID = ? and page_no = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.select(sql, params);
        db.close();
    }

    public Table getFavoritePages(Context context)
    {
        Table table;

        String sql = "SELECT * FROM part_content WHERE favorite like 'yes'";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql);
        db.close();
        return table;
    }

    public void addNote(Context context, String note, Integer part_ID,Integer page_no)
    {
        String params[] = {String.valueOf(note), String.valueOf(part_ID) ,String.valueOf(page_no)};
        String sql = "UPDATE part_content SET note = ? WHERE part_ID = ? and page_no = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.select(sql, params);
        db.close();
    }

    public void deleteNote(Context context, Integer part_content_ID)
    {
        String params[] = {String.valueOf(part_content_ID)};
        String sql = "UPDATE part_content SET note ='no' WHERE ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.select(sql, params);
        db.close();
    }

    public Table getNoticePages(Context context)
    {
        Table table;

        String sql = "SELECT * FROM part_content WHERE note <> 'no'";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql);
        db.close();
        return table;
    }

    public String getNoticOfPage(Context context, Integer part_ID,Integer page_no)
    {
        Table table;
        String params[] = {String.valueOf(part_ID),String.valueOf(page_no)};
        String sql = "SELECT * FROM part_content WHERE part_ID = ? and page_no =?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        return table.get(0,"note");
    }

    public Integer getDefultPage(Context context, Integer part_ID)
    {
        Integer defult_page;
        Table table;
        String params[] = {String.valueOf(part_ID)};
        String sql = "SELECT page_no FROM defult_pages WHERE part_ID = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        defult_page = Integer.valueOf(table.get(0, "page_no"));
        return defult_page;
    }

    public Table getSearchPage(Context context, Integer part_ID,String s)
    {

        Table table;
        String params[] = {String.valueOf(part_ID)};
        String sql = "SELECT * FROM part_content WHERE part_ID = ? and content like '%"+ s +"%' ";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        return table;
    }
    public Table getSearchPage(Context context,String s)
    {

        Table table;

        String sql = "SELECT * FROM part_content WHERE content like '%"+ s +"%' ";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql);
        db.close();
        return table;
    }

    public Integer getpageInQestion(Context context, Integer part_ID,Integer q_no)
    {
        Integer pageInQestion=0;

        Table table;
        String params[] = {String.valueOf(part_ID)};
        String sql = "SELECT page_no FROM part_content WHERE part_ID = ? and content like '%"+ q_no +"%'";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        if(table.ROWS>0)
        {
            pageInQestion = Integer.valueOf(table.get(0, "page_no"));
        }

        return pageInQestion;
    }

    public boolean getIfFavorite(Context context, Integer part_ID, Integer page_no)
    {
        String favorite;
        Table table;
        String params[] = {String.valueOf(part_ID), String.valueOf(page_no)};
        String sql = "SELECT favorite FROM part_content WHERE part_ID = ? and page_no = ?";
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        table = db.select(sql, params);
        db.close();
        favorite = table.get(0, "favorite");
        return (!favorite.equals("yes"));
    }

    public void setDefultPage(Context context, Integer part_ID, Integer page_no)
    {
        String sql = "UPDATE  defult_pages SET page_no =" + page_no + " WHERE part_ID = " + part_ID;
        DatabaseAdapter db = new DatabaseAdapter(context);
        db.open();
        db.execute(sql);
        db.close();
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public Integer getPart_ID()
    {
        return part_ID;
    }

    public void setPart_ID(Integer part_ID)
    {
        this.part_ID = part_ID;
    }

    public Integer getPage_no()
    {
        return page_no;
    }

    public void setPage_no(Integer page_no)
    {
        this.page_no = page_no;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getFootnote()
    {
        return footnote;
    }

    public void setFootnote(String footnote)
    {
        this.footnote = footnote;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getFavorite()
    {
        return favorite;
    }

    public void setFavorite(String favorite)
    {
        this.favorite = favorite;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage_path()
    {
        return image_path;
    }

    public void setImage_path(String image_path)
    {
        this.image_path = image_path;
    }
}
