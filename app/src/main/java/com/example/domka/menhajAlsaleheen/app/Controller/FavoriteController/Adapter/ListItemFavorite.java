package com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter;

/**
 * Created by domka on 2017-05-26.
 */
public class ListItemFavorite
{
     String content;
     Integer part_ID;
     Integer page_no;


    public ListItemFavorite(String content, Integer part_ID, Integer page_no)
    {
        this.content = content;
        this.part_ID = part_ID;
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



}
