package com.example.ejercicio_unoauno;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    private  static SessionFactory sessionFactory;
    public static void main(String[] args) {

        try {
            setUp();
            hacerCosas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Con este método se inicia y se prepara todo para la conexión con la base de datos
     * @throws Exception
     */
    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }


    protected  static  void hacerCosas(){

        Direccion direccion=new Direccion(1, "Plaza del ayuntamiento", 8, "Xativa", "Valencia");
        Alumnado profesor=new Alumnado("Juan", "Perez", "García", new Direccion(1,"Plaza del ayuntamiento", 8, "Xativa", "Valencia"));
        profesor.setDireccion(direccion);

        Session session=sessionFactory.openSession();
        session.beginTransaction();

        session.save(profesor);

        session.getTransaction().commit();
        session.close();
    }
}
