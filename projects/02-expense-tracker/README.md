# Pennywise — Expense Tracker

A responsive, local-first personal finance tracker built with HTML, CSS, and vanilla JavaScript. It uses Jordanian dinars (JOD) for display.

## Features

- Record income and expenses with descriptions, categories, and dates.
- Current-month income, expense, and net-balance summary.
- Expense totals grouped by category.
- Filter transaction history by month.
- Delete transactions and export all records to CSV.
- Persists records in browser `localStorage`.

## Run locally

Open `index.html` in a modern browser, or serve this directory with VS Code Live Server.

## Data and privacy

Transactions remain in the browser's local storage. There is no backend, account, or cloud synchronization. Clearing browser site data will remove saved transactions. Do not use this learning project as the sole record of important financial information.

## Learning checklist

- [ ] Trace how a form submission becomes a transaction object.
- [ ] Explain how `reduce()` calculates the monthly totals.
- [ ] Explain how categories are grouped for the spending chart.
- [ ] Add an edit transaction feature.
- [ ] Add a monthly budget and an overspending warning.

## Tech stack

HTML5 · CSS3 · Vanilla JavaScript · Browser localStorage
