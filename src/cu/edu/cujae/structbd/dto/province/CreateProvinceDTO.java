package cu.edu.cujae.structbd.dto.province;

public class CreateProvinceDTO
{

    private String ID;
    private String name;

    public CreateProvinceDTO(String ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    public String getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public void setID(String ID)
    {
        if (ID != null)
        {
            this.ID = ID;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public void setName(String name)
    {
        if (name != null)
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

}
