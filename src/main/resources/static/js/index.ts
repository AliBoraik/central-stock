/**
 * This function must be on a td inside a tr that is immediately followed by an hidden <tr/>
 * @param el - the td element clicked
 */
function toggleShow(el: HTMLElement) {
    const tr: HTMLElement = <HTMLElement> el.parentElement.nextElementSibling // next row
    const display = tr.style.display
    if (display.trim().length === 0) {
        // show
        tr.style.display = 'table-row'
        el.getElementsByClassName('btn')[0].innerHTML = '&uparrow;';
    } else {
        // hide
        tr.style.removeProperty('display')
        el.getElementsByClassName('btn')[0].innerHTML = '&downarrow;'
    }
}