const KEY = "pennywise.transactions.v1";
const $ = selector => document.querySelector(selector);
const form = $("#transaction-form");
let transactions = load();
function load(){try{const data=JSON.parse(localStorage.getItem(KEY)||"[]");return Array.isArray(data)?data.filter(x=>x&&typeof x.id==="string"&&Number.isFinite(x.amount)):[]}catch(e){console.warn("Could not load transactions",e);return[]}}
function save(){try{localStorage.setItem(KEY,JSON.stringify(transactions))}catch(e){alert("Could not save data in this browser.");console.error(e)}}
function money(value){return new Intl.NumberFormat("en-JO",{style:"currency",currency:"JOD"}).format(value)}
function monthKey(date){return date.slice(0,7)}
function render(){
 const currentMonth=new Date().toISOString().slice(0,7);
 const monthly=transactions.filter(t=>monthKey(t.date)===currentMonth);
 const income=monthly.filter(t=>t.type==="income").reduce((s,t)=>s+t.amount,0);
 const expenses=monthly.filter(t=>t.type==="expense").reduce((s,t)=>s+t.amount,0);
 $("#balance").textContent=money(income-expenses);$("#income").textContent=money(income);$("#expenses").textContent=money(expenses);
 const months=[...new Set(transactions.map(t=>monthKey(t.date)))].sort().reverse();
 const monthFilter=$("#month-filter");const old=monthFilter.value;
 monthFilter.replaceChildren(new Option("All months","all"));
 months.forEach(m=>monthFilter.add(new Option(new Date(m+"-02").toLocaleDateString("en",{month:"long",year:"numeric"}),m)));
 monthFilter.value=months.includes(old)?old:"all";
 const filtered=transactions.filter(t=>monthFilter.value==="all"||monthKey(t.date)===monthFilter.value).sort((a,b)=>b.date.localeCompare(a.date));
 const tbody=$("#transaction-list");tbody.replaceChildren();
 filtered.forEach(t=>{const tr=document.createElement("tr");[t.description,t.category,new Date(t.date+"T12:00:00").toLocaleDateString()].forEach(value=>{const td=document.createElement("td");td.textContent=value;tr.append(td)});
 const amount=document.createElement("td");amount.className=t.type==="income"?"amount-income":"amount-expense";amount.textContent=(t.type==="income"?"+":"−")+money(t.amount);tr.append(amount);
 const action=document.createElement("td");const button=document.createElement("button");button.className="delete";button.textContent="×";button.setAttribute("aria-label","Delete transaction");button.addEventListener("click",()=>{transactions=transactions.filter(x=>x.id!==t.id);save();render()});action.append(button);tr.append(action);tbody.append(tr)});
 $("#history-empty").hidden=filtered.length>0;
 const byCategory={};transactions.filter(t=>t.type==="expense").forEach(t=>byCategory[t.category]=(byCategory[t.category]||0)+t.amount);
 const entries=Object.entries(byCategory).sort((a,b)=>b[1]-a[1]);const total=entries.reduce((s,e)=>s+e[1],0);const breakdown=$("#breakdown");breakdown.replaceChildren();
 entries.forEach(([category,value])=>{const wrap=document.createElement("div");const label=document.createElement("div");label.className="bar-label";const name=document.createElement("span");name.textContent=category;const amount=document.createElement("span");amount.textContent=money(value);label.append(name,amount);const bar=document.createElement("div");bar.className="bar";const fill=document.createElement("span");fill.style.width=(total?value/total*100:0)+"%";bar.append(fill);wrap.append(label,bar);breakdown.append(wrap)});
 $("#breakdown-empty").hidden=entries.length>0;
}
form.addEventListener("submit",event=>{event.preventDefault();const amount=Number($("#amount").value);const date=$("#date").value;if(!Number.isFinite(amount)||amount<=0||!date){alert("Enter a valid positive amount and date.");return}
 transactions.push({id:crypto.randomUUID?crypto.randomUUID():String(Date.now()),description:$("#description").value.trim(),amount,type:$("#type").value,category:$("#category").value,date});save();form.reset();$("#date").value=new Date().toISOString().slice(0,10);render()});
$("#month-filter").addEventListener("change",render);
$("#export").addEventListener("click",()=>{if(!transactions.length){alert("There are no transactions to export.");return}const rows=[["Description","Amount_JOD","Type","Category","Date"],...transactions.map(t=>[t.description,t.amount.toFixed(2),t.type,t.category,t.date])];const csv=rows.map(row=>row.map(cell=>'"'+String(cell).replace(/"/g,'""')+'"').join(",")).join("\r\n");const url=URL.createObjectURL(new Blob([csv],{type:"text/csv;charset=utf-8"}));const a=document.createElement("a");a.href=url;a.download="pennywise-transactions.csv";a.click();URL.revokeObjectURL(url)});
$("#date").value=new Date().toISOString().slice(0,10);render();