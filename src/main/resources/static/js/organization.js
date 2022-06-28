var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
function getProducts(el, ids) {
    return __awaiter(this, void 0, void 0, function () {
        var hiddenRow, button, requests, jsonResult, _i, requests_1, req, _a, _b, table, headers;
        return __generator(this, function (_c) {
            switch (_c.label) {
                case 0:
                    hiddenRow = el.parentElement.nextElementSibling;
                    button = el.getElementsByClassName('btn')[0];
                    button.disabled = true;
                    button.innerHTML = "<span class=\"spinner-grow spinner-grow-sm\" role=\"status\" aria-hidden=\"true\"></span>\nLoading...";
                    if (hiddenRow.style.display.length !== 0) {
                        // simply close by calling dependent function
                        button.disabled = false;
                        toggleShow(el);
                        return [2 /*return*/, null];
                    }
                    return [4 /*yield*/, Promise.all(ids.map(function (id) { return fetch("/central/product/" + id); }))];
                case 1:
                    requests = _c.sent();
                    jsonResult = [];
                    _i = 0, requests_1 = requests;
                    _c.label = 2;
                case 2:
                    if (!(_i < requests_1.length)) return [3 /*break*/, 5];
                    req = requests_1[_i];
                    _b = (_a = jsonResult).push;
                    return [4 /*yield*/, req.json()];
                case 3:
                    _b.apply(_a, [_c.sent()]);
                    _c.label = 4;
                case 4:
                    _i++;
                    return [3 /*break*/, 2];
                case 5:
                    table = hiddenRow.getElementsByClassName('table')[0];
                    // empty the rows if there are data
                    if (table.rows.length !== 0) {
                        table.innerHTML = "";
                    }
                    headers = ['#ID', 'Name', 'Code', 'Price', 'Count'];
                    insertHeader(table, headers);
                    insertItems(table, jsonResult);
                    // hide loading spinner
                    button.disabled = false;
                    toggleShow(el);
                    return [2 /*return*/];
            }
        });
    });
}
function insertHeader(table, headers) {
    var row = table.insertRow(-1);
    row.innerHTML = '';
    for (var _i = 0, headers_1 = headers; _i < headers_1.length; _i++) {
        var head = headers_1[_i];
        row.innerHTML += '<th>' + head + '</th>';
    }
}
function insertItems(table, items) {
    for (var _i = 0, items_1 = items; _i < items_1.length; _i++) {
        var req = items_1[_i];
        // append a new row
        var row = table.insertRow(-1);
        row.id = "" + req.productid;
        var cell1 = row.insertCell(-1);
        cell1.innerHTML = req.productid;
        var cell2 = row.insertCell(-1);
        cell2.innerHTML = req.name;
        var cell3 = row.insertCell(-1);
        cell3.innerHTML = req.code;
        var cell4 = row.insertCell(-1);
        cell4.innerHTML = "" + req.price;
        var cell5 = row.insertCell(-1);
        cell5.innerHTML = req.count + " <span class=\"unit\"></span> left";
    }
    fetchProductUnits(items.map(function (it) { return ({ innerId: it.productid, unit: it.unit }); }));
}
