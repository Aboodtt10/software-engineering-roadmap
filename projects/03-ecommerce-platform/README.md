# Northstar Market — E-Commerce Front-End Demo

A responsive sample storefront built with **HTML, CSS, and vanilla JavaScript**. This project practices product rendering, category filters, search, cart state, quantity updates, browser persistence, and a simulated checkout flow.

## Features

- Product catalog with sample product data.
- Category filtering and live search.
- Add products to cart and change quantities.
- Cart subtotal and item count.
- Cart persists in browser `localStorage`.
- Simulated checkout message; no real order or payment is created.
- Responsive layout for mobile and desktop.

## Run locally

Open `index.html` in a modern browser, or serve this directory with VS Code Live Server.

## Project structure

```text
03-ecommerce-platform/
├── index.html
├── styles.css
├── app.js
└── README.md
```

## Learning checklist

- [ ] Explain how product data is rendered into DOM elements.
- [ ] Trace how adding a product changes cart state.
- [ ] Explain why cart data is saved in `localStorage`.
- [ ] Add a product detail view.
- [ ] Replace sample products with a REST API.
- [ ] Build a Java Spring Boot backend with PostgreSQL.
- [ ] Add secure user authentication and automated tests.

## Important limitations

This is a **front-end demo only**. It has no backend, user accounts, inventory synchronization, order storage, or payment integration. The checkout is simulated and collects no payment details. Do not treat it as a production store.

## Tech stack

HTML5 · CSS3 · Vanilla JavaScript · Browser localStorage
