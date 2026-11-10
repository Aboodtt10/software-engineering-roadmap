const STORAGE_KEY = "taskflow.tasks.v1";
const list = document.querySelector("#task-list");
const form = document.querySelector("#task-form");
const input = document.querySelector("#task-input");
const priorityInput = document.querySelector("#priority");
const searchInput = document.querySelector("#search");
const empty = document.querySelector("#empty");
let tasks = loadTasks();
let filter = "all";

function loadTasks() {
  try {
    const value = JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]");
    return Array.isArray(value) ? value.filter(t => t && typeof t.id === "string" && typeof t.title === "string") : [];
  } catch (error) {
    console.warn("Could not read saved tasks:", error);
    return [];
  }
}
function saveTasks() {
  try { localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks)); }
  catch (error) { alert("Your browser could not save tasks. Check available storage."); console.error(error); }
}
function render() {
  const query = searchInput.value.trim().toLowerCase();
  const visible = tasks.filter(task => {
    const matchesFilter = filter === "all" || (filter === "active" && !task.done) || (filter === "completed" && task.done);
    return matchesFilter && task.title.toLowerCase().includes(query);
  });
  list.replaceChildren();
  visible.forEach(task => {
    const item = document.createElement("li");
    item.className = `task ${task.done ? "done" : ""}`;
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox"; checkbox.checked = task.done; checkbox.setAttribute("aria-label", `Mark ${task.title} complete`);
    checkbox.addEventListener("change", () => { task.done = checkbox.checked; saveTasks(); render(); });
    const copy = document.createElement("div"); copy.className = "task-copy";
    const title = document.createElement("span"); title.className = "task-title"; title.textContent = task.title;
    copy.append(title);
    const badge = document.createElement("span"); badge.className = `priority ${task.priority}`; badge.textContent = task.priority;
    const remove = document.createElement("button"); remove.className = "delete"; remove.type = "button"; remove.textContent = "×"; remove.setAttribute("aria-label", `Delete ${task.title}`);
    remove.addEventListener("click", () => { tasks = tasks.filter(t => t.id !== task.id); saveTasks(); render(); });
    item.append(checkbox, copy, badge, remove); list.append(item);
  });
  empty.hidden = visible.length > 0;
  document.querySelector("#total").textContent = tasks.length;
  document.querySelector("#completed").textContent = tasks.filter(t => t.done).length;
  document.querySelector("#remaining").textContent = tasks.filter(t => !t.done).length;
  document.querySelector("#count-label").textContent = `${visible.length} of ${tasks.length} tasks`;
}
form.addEventListener("submit", event => {
  event.preventDefault();
  const title = input.value.trim();
  if (!title) return;
  tasks.unshift({ id: crypto.randomUUID ? crypto.randomUUID() : String(Date.now()), title, priority: priorityInput.value, done: false, createdAt: new Date().toISOString() });
  saveTasks(); form.reset(); priorityInput.value = "medium"; input.focus(); render();
});
document.querySelectorAll("[data-filter]").forEach(button => button.addEventListener("click", () => {
  filter = button.dataset.filter;
  document.querySelectorAll("[data-filter]").forEach(b => b.classList.toggle("active", b === button));
  render();
}));
searchInput.addEventListener("input", render);
document.querySelector("#clear-completed").addEventListener("click", () => { tasks = tasks.filter(t => !t.done); saveTasks(); render(); });
document.querySelector("#today").textContent = new Intl.DateTimeFormat("en", { weekday: "short", month: "short", day: "numeric" }).format(new Date());
render();