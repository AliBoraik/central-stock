interface ProductInterface {
    productid: string;
    count: number;
    code: string;
    name: string;
    price: number;
    unit: number;
}

async function getProducts(el: HTMLElement, ids: string[]) {
    // check if button is open then close without calling fetch else call fetch
    const hiddenRow: HTMLElement = <HTMLElement>el.parentElement.nextElementSibling;

    // change button to loading button
    const button: HTMLButtonElement = <HTMLButtonElement>el.getElementsByClassName('btn')[0];
    button.disabled = true;
    button.innerHTML = `<span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
Loading...`;

    if (hiddenRow.style.display.length !== 0) {
        // simply close by calling dependent function
        button.disabled = false;
        toggleShow(el);
        return null;
    }

    // @ts-ignore
    const requests = await Promise.all(ids.map(id => fetch(`/central/product/${id}`)))
    const jsonResult: ProductInterface[] = []
    for (const req of requests) {
        jsonResult.push(await req.json());
    }

    const table: HTMLTableElement = <HTMLTableElement>hiddenRow.getElementsByClassName('table')[0];
    // empty the rows if there are data
    if (table.rows.length !== 0) {
        table.innerHTML = "";
    }
    const headers = ['#ID', 'Name', 'Code', 'Price', 'Count']
    insertHeader(table, headers);
    insertItems(table,jsonResult);

    // hide loading spinner
    button.disabled = false;
    toggleShow(el);
}

function insertHeader(table: HTMLTableElement, headers: string[]) {
    const row = table.insertRow(-1);
    row.innerHTML = ''
    for (const head of headers) {
        row.innerHTML += '<th>'+head+'</th>';
    }
}

function insertItems(table: HTMLTableElement, items: ProductInterface[]) {
    for (const req of items) {
        // append a new row
        const row = table.insertRow(-1);
        row.id = `${req.productid}`;
        const cell1 = row.insertCell(-1);
        cell1.innerHTML = req.productid
        const cell2 = row.insertCell(-1);
        cell2.innerHTML = req.name;
        const cell3 = row.insertCell(-1);
        cell3.innerHTML = req.code;
        const cell4 = row.insertCell(-1);
        cell4.innerHTML = `${req.price}`;
        const cell5 = row.insertCell(-1);
        cell5.innerHTML = `${req.count} <span class="unit"></span> left`
    }
    fetchProductUnits(items.map(it => ({innerId: it.productid, unit: it.unit})))
}