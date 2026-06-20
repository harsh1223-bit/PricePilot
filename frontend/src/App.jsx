import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Products from "./pages/Products";
import ShoppingLists from "./pages/ShoppingLists";
import Recommendations from "./pages/Recommendations";
import Optimization from "./pages/Optimization";
import LivePrices from "./pages/LivePrices";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />

        <Route path="/dashboard" element={<Dashboard />} />

        <Route path="/products" element={<Products />} />

        <Route
          path="/shopping-lists"
          element={<ShoppingLists />}
        />

        <Route
          path="/recommendations"
          element={<Recommendations />}
        />

        <Route
          path="/optimization"
          element={<Optimization />}
        />

        <Route
          path="/live-prices"
          element={<LivePrices />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;