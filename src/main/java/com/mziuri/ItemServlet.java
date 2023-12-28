package com.mziuri;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
*           WARNING!
*   es kodi rogorc wina ar shemimowmebia,
*   radgan dro ar meko amirom icodet ar vi
*   vici rogor mushaobs da tu mushaobs saertod!
*
*   es kodi daiwere shemdegnairad avige tqveni kodi
*   shemdeg davgugle ragaceebi da davwere!
*
* */
public class ItemServlet extends HttpServlet {
    private Map<String, Float> products = new HashMap<>();

    public void init() {
        StoreItem milk = new StoreItem("milk", (float) 4.98);
        StoreItem cheese = new StoreItem("Cheese", (float)22.42);

        products.put(milk.getName(), milk.getPrice());
        products.put(cheese.getName(), cheese.getPrice());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));

        if (name.length() < 5 || name.length() > 20 || price <= 0 || products.containsKey(name)) {
            throw new IllegalArgumentException("araswored sheiyvane ragaca");
        }

        products.put(name, price);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (name.length() < 5 || name.length() > 20 || !products.containsKey(name)) {
            throw new IllegalArgumentException("ragac shegeshala sheyvanisas");
        }

        products.remove(name);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));;

        if (name.length() < 5 || name.length() > 20 || price <= 0 || !products.containsKey(name)) {
            throw new IllegalArgumentException("ragac shegeshala sheyvanisas");
        }

        products.put(name, price);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (name != null && (name.length() < 5 || name.length() > 20)) {
            throw new IllegalArgumentException("araswori productis saxeli");
        }
        if (name != null) {
            if (products.containsKey(name)) {
                double price = products.get(name);
                response.getWriter().write(name + ": " + price);
            }
            else {
                response.getWriter().write("Product ver moidzebna");
            }
        }
        else {
            for (Map.Entry<String, Float> entry : products.entrySet()) {
            response.getWriter().write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }
}