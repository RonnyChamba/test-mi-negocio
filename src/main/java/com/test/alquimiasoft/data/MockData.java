package com.test.alquimiasoft.data;

import com.test.alquimiasoft.model.Customer;
import com.test.alquimiasoft.model.Subsidiary;

public class MockData {


    public static final Customer CUSTOMER_1 = new Customer(1,
            "CEDULA",
            "1723774640",
            "Pamela Valdez",
            "pamelavaldez@gmail.com",
            "0981806210");

    public static final Customer CUSTOMER_2 = new Customer(2,
            "CEDULA",
            "2356412150",
            "Pedro Saurez",
            "pedrodos@gmail.com",
            "0981805620");


    public static final Customer CUSTOMER_3 = new Customer(3,
            "RUC",
            "2356412150231",
            "Maria Perez",
            "mariaperez@gmail.com",
            "0900632102");


    public static final Customer CUSTOMER_4 = new Customer(4,
            "RUC",
            "2356412150299",
            "Marlon Chamba",
            "marlonchamba2023@gmail.com",
            "0989745620");

    public static final Subsidiary SUBSIDIARY_1 = new Subsidiary(
            1,
            "Azuay",
            "Azogues",
            "Av. 12 de Abril y Av. Loja",
            Boolean.TRUE);

    public static final Subsidiary SUBSIDIARY_2 = new Subsidiary(
            2,
            "Azuay",
            "Cuenca",
            "Calle Larga y Huayna Capac",
            Boolean.FALSE);

    public static final Subsidiary SUBSIDIARY_3 = new Subsidiary(
            3,
            "Santa Elena",
            "Salinas",
            "Av. Malecón y Calle 12",
            Boolean.FALSE);

    public static final Subsidiary SUBSIDIARY_4 = new Subsidiary(
            4,
            "Santo Domingo de los Tsáchilas",
            "Santa Domingo",
            "Av. Quito e Ibarra",
            Boolean.TRUE);


    public static final Subsidiary SUBSIDIARY_5 = new Subsidiary(
            5,
            "Santo Domingo de los Tsáchilas",
            "La Concordia",
            "Terminal Terrestre y Av. Quito",
            Boolean.FALSE);

    public static final Subsidiary SUBSIDIARY_6 = new Subsidiary(
            6,
            "Manabí",
            "El Carmen",
            "Calle 3 de Julio y restrepo",
            Boolean.TRUE);

    public static final Subsidiary SUBSIDIARY_7 = new Subsidiary(
            7,
            "Manabí",
            "Portoviejo",
            "Pueblo Viejo y Av. Manabí",
            Boolean.FALSE);

    public static final Subsidiary SUBSIDIARY_8 = new Subsidiary(
            8,
            "El Oro",
            "Piñas",
            "Cas de las Palmas y Av. 9 de Octubre",
            Boolean.TRUE);

    public static final Subsidiary SUBSIDIARY_9 = new Subsidiary(
            9,
            "Los Rios",
            "Quevedo",
            "Av. no se que y Av. no se que",
            Boolean.TRUE);



}
