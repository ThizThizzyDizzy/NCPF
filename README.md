# NCPF Specifications (VERY WIP)
*For Legacy NCPF versions, see [NCPF Format and Configuration Guidelines](https://docs.google.com/document/d/1dzU2arDrD7n9doRua8laxzRy9_RtX-cuv1sUJBB5aGY/edit#heading=h.a5zsimflpf1b)*

# File Format
NCPF is no longer just a file type, but rather a data structure, and can be saved in any file format that supports it.
Files should have a dual extension, with `.ncpf` followed by the extension of the format (For example, `.ncpf.json`)
Another prefix extension may be added as well to help denote the contents of the file; (ex. `.cfg.ncpf.json`) There is no defined naming convention for prefix extensions

Any file format that supports the neccesary data structure is supported.

# Primitive Types
- Object (Contains String-keyed elements in no particular order)
- Boolean
- String
- Integer (32 bit signed)
- Float (32 bit)
- List (An ordered list of elements, contains many items of one type)

# Complex Types
Commonly used types within NCPF that are standardized and defined here. They are all objects, containing the listed substrucutre

## Element
Refers to a block, item, fluid, or something else present ingame.

Elements have the following structure:
- **String** `type`
- (additional values depending on the type, listed below)

Here is a list of all defined types:
- `legacy_block` (for 1.12 and below)
- `legacy_item` (for 1.12 and below)
- `legacy_fluid` (for 1.12 and below)
- `oredict` (for 1.12 and below; use tags for newer versions)
- `block` (1.13+)
- `item` (1.13+)
- `fluid` (1.13+)
- `block_tag`
- `item_tag`
- `fluid_tag`

Custom types are also allowed.
It is highly reccommended to identify custom types to avoid compatibility issues (For example, using a namespaced key), but it is not required.

### Legacy Block
A block with optional metadata or NBT (1.12 and below)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stone`)
- **Integer** `metadata` (must only be included if the block has multiple states)
- **String** `nbt` (Optional; only for block entities. This is in SNBT format)

### Legacy Item
An item with optional metadata or NBT (1.12 and below)
- **String** `name`
- **Integer** `metadata` (must only be included if the item has variants)
- **String** `nbt` (Optional; This is in SNBT format)

### Legacy Fluid
A fluid (1.12 and below)
- **String** `name`

### Oredict
An collection of blocks or items defined by the oredict
- **String** `oredict`

### Block
A block with optional blockstates or NBT (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stone`)
- **Object** `blockstate` (optional, only include if a specific state or states are required)
This contains all required states, with the state name as the key, and the value as either an `Integer` or `String`
*Numerical values may not be saved as strings*
- **String** `nbt` (Optional; only for block entities.  This is in SNBT format)

### Item
An item with optional NBT (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stick`)
- **String** `nbt` (Optional; This is in SNBT format)

### Fluid
A fluid (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:water`)

### Block Tag
A collection of blocks with optional blockstates or NBT (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:fences`)
- **Object** `blockstate` (optional, only include if a specific state or states are required)
This contains all required states, with the state name as the key, and the value as either an `Integer` or `String`
*Numerical values may not be saved as strings*
- **String** `nbt` (Optional; only for block entities.  This is in SNBT format)

### Item Tag
A collection of items with optional NBT (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:fences`)
- **String** `nbt` (Optional; This is in SNBT format)

### Fluid Tag
A collection of fluids (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:water`)