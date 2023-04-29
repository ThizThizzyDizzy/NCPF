# NCPF Specifications (VERY WIP)
*For Legacy NCPF versions, see [NCPF Format and Configuration Guidelines](https://docs.google.com/document/d/1dzU2arDrD7n9doRua8laxzRy9_RtX-cuv1sUJBB5aGY/edit#heading=h.a5zsimflpf1b)*

# File Format
NCPF is no longer just a file type, but rather a data structure, and can be saved in any file format that supports it.
Files should have a dual extension, with `.ncpf` followed by the extension of the format (For example, `.ncpf.json`)

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
- Legacy Block (for 1.12 and below)
- Legacy Item (for 1.12 and below)
- Legacy Fluid (for 1.12 and below)
- Oredict (for 1.12 and below; use tags for newer versions)
- Block (1.13+)
- Item (1.13+)
- Fluid (1.13+)
- Block Tag
- Item Tag
- Fluid Tag

Custom types are also allowed.
It is highly reccommended to identify custom types to avoid compatibility issues (For example, using a namespaced key), but it is not required.

### Legacy Block
A block with optional metadata or NBT (1.12 and below)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stone`)
- **Integer** `metadata` (must only be included if the block has multiple states)
- **String** `nbt` (Optional; only for block entities)

### Legacy Item
An item with optional metadata or NBT (1.12 and below)
- **String** `name`
- **Integer** `metadata` (must only be included if the item has variants)
- **String** `nbt` (Optional)

### Legacy Fluid
A fluid (1.12 and below)
- **String** `name`

### Oredict
### Item
### Block
### Fluid
### Block Tag
### Item Tag
### Fluid Tag
